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
    private val getPokemonsByNameFilteredByType: GetPokemonsByNameFilteredByType,
    private val getPokemonsByNameFilteredByMultiType: GetPokemonsByNameFilteredByMultiType,
    private val getFavoritePokemon: GetFavoritePokemon,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private val isFavorite: IsFavorite
): ViewModel() {
    var pokemonModel = MutableLiveData<List<FilteredPokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    var updateScope: Job? = null
    var scope: Job? = null

    fun pokemonSearch(pokemonName: String, types: List<String>) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = typeFilteredSearch(pokemonName, types)
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }
    }

    fun favoritePokemonSearch(pokemonName: String, types: List<String>){
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = typeFilteredFavoriteSearch(pokemonName, types)
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

    suspend fun typeFilteredSearch(pokemonName: String, types: List<String>): List<FilteredPokemon> {
        var pokemons: List<FilteredPokemon> = emptyList()
        when(types.size){
            0 -> pokemons = getPokemonsByName(pokemonName)
            1 -> pokemons = getPokemonsByNameFilteredByType(pokemonName, types[0])
            2 -> pokemons = getPokemonsByNameFilteredByMultiType(pokemonName, types[0], types[1])
        }
        return pokemons
    }

    suspend fun typeFilteredFavoriteSearch(pokemonName: String, types: List<String>): List<FilteredPokemon> {
        var pokemons: List<FilteredPokemon> = emptyList()
        when(types.size){
            0 -> pokemons = getPokemonsByName(pokemonName)
            1 -> pokemons = getPokemonsByNameFilteredByType(pokemonName, types[0])
            2 -> pokemons = getPokemonsByNameFilteredByMultiType(pokemonName, types[0], types[1])
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