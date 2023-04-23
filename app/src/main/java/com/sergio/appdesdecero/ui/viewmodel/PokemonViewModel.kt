package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.domain.*
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemons: GetPokemons,
    private val getPokemonsByName: GetPokemonsByName,
    private val getPokemonsByNameAZ: GetPokemonsByNameAZ,
    private val getPokemonsByNameZA: GetPokemonsByNameZA,
    private val getPokemonsByNameFilteredByType: GetPokemonsByNameFilteredByType,
    private val getPokemonsByNameFilteredByTypeAZ: GetPokemonsByNameFilteredByTypeAZ,
    private val getPokemonsByNameFilteredByTypeZA: GetPokemonsByNameFilteredByTypeZA,
    private val getPokemonsByNameFilteredByMultiType: GetPokemonsByNameFilteredByMultiType,
    private val getPokemonsByNameFilteredByMultiTypeAZ: GetPokemonsByNameFilteredByMultiTypeAZ,
    private val getPokemonsByNameFilteredByMultiTypeZA: GetPokemonsByNameFilteredByMultiTypeZA,
    private val getFavoritePokemon: GetFavoritePokemon,
    private val getFavoritePokemonAZ: GetFavoritePokemonAZ,
    private val getFavoritePokemonZA: GetFavoritePokemonZA,
    private val getFavoritePokemonsByNameFilteredByType: GetFavoritePokemonsByNameFilteredByType,
    private val getFavoritePokemonsByNameFilteredByTypeAZ: GetFavoritePokemonsByNameFilteredByTypeAZ,
    private val getFavoritePokemonsByNameFilteredByTypeZA: GetFavoritePokemonsByNameFilteredByTypeZA,
    private val getFavoritePokemonsByNameFilteredByMultiType: GetFavoritePokemonsByNameFilteredByMultiType,
    private val getFavoritePokemonsByNameFilteredByMultiTypeAZ: GetFavoritePokemonsByNameFilteredByMultiTypeAZ,
    private val getFavoritePokemonsByNameFilteredByMultiTypeZA: GetFavoritePokemonsByNameFilteredByMultiTypeZA,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private val isFavorite: IsFavorite
): ViewModel() {
    var pokemonModel = MutableLiveData<List<FilteredPokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    var updateScope: Job? = null
    var scope: Job? = null

    fun pokemonSearch(pokemonName: String, ordering: String, types: List<String>) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = typeFilteredSearch(pokemonName, ordering, types)
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }
    }

    fun favoritePokemonSearch(pokemonName: String, ordering: String, types: List<String>){
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = typeFilteredFavoriteSearch(pokemonName, ordering, types)
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }
    }
    fun updateDatabase(){
        updateScope = viewModelScope.launch {
            Log.v("TESTING", "STARTING DATABASE UPDATE")
            getPokemons()
            Log.v("TESTING", "FINISHING DATABASE UPDATE")
        }
    }

    suspend fun typeFilteredSearch(pokemonName: String, ordering: String, types: List<String>): List<FilteredPokemon> {
        var pokemons: List<FilteredPokemon> = emptyList()
        when(ordering){
            "" -> {
                when(types.size){
                    0 -> pokemons = getPokemonsByName(pokemonName)
                    1 -> pokemons = getPokemonsByNameFilteredByType(pokemonName, types[0])
                    2 -> pokemons = getPokemonsByNameFilteredByMultiType(pokemonName, types[0], types[1])
                }
            }

            "az" -> {
                when(types.size){
                    0 -> pokemons = getPokemonsByNameAZ(pokemonName)
                    1 -> pokemons = getPokemonsByNameFilteredByTypeAZ(pokemonName, types[0])
                    2 -> pokemons = getPokemonsByNameFilteredByMultiTypeAZ(pokemonName, types[0], types[1])
                }
            }

            "za" -> {
                when(types.size){
                    0 -> pokemons = getPokemonsByNameZA(pokemonName)
                    1 -> pokemons = getPokemonsByNameFilteredByTypeZA(pokemonName, types[0])
                    2 -> pokemons = getPokemonsByNameFilteredByMultiTypeZA(pokemonName, types[0], types[1])
                }
            }
        }
        return pokemons
    }

    suspend fun typeFilteredFavoriteSearch(pokemonName: String, ordering: String, types: List<String>): List<FilteredPokemon> {
        var pokemons: List<FilteredPokemon> = emptyList()

        when(ordering){
            "" -> {
                when(types.size){
                    0 -> pokemons = getFavoritePokemon(pokemonName)
                    1 -> pokemons = getFavoritePokemonsByNameFilteredByType(pokemonName, types[0])
                    2 -> pokemons = getFavoritePokemonsByNameFilteredByMultiType(pokemonName, types[0], types[1])
                }
            }

            "az" -> {
                when(types.size){
                    0 -> pokemons = getFavoritePokemonAZ(pokemonName)
                    1 -> pokemons = getFavoritePokemonsByNameFilteredByTypeAZ(pokemonName, types[0])
                    2 -> pokemons = getFavoritePokemonsByNameFilteredByMultiTypeAZ(pokemonName, types[0], types[1])
                }
            }

            "za" -> {
                when(types.size){
                    0 -> pokemons = getFavoritePokemonZA(pokemonName)
                    1 -> pokemons = getFavoritePokemonsByNameFilteredByTypeZA(pokemonName, types[0])
                    2 -> pokemons = getFavoritePokemonsByNameFilteredByMultiTypeZA(pokemonName, types[0], types[1])
                }
            }
        }
        return pokemons
    }

    suspend fun addFavoritePokemon(name: String){
        addFavorite(name)
    }

    suspend fun removeFavoritePokemon(name: String){
        removeFavorite(name)
    }

    suspend fun isFavoritePokemon(name: String): Boolean{
        return isFavorite(name)
    }

}