package com.sergio.appdesdecero.ui.viewmodel

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
    var favoriteModel = MutableLiveData<List<FilteredPokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    var scope: Job? = null


    fun pokemonSearch(pokemonName: String, types: List<String>) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            getPokemons()

            val pokemons = typeFilteredSearch(pokemonName, types)
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }
    }

    fun favoritePokemonSearch(pokemonName: String){
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = getFavoritePokemon(pokemonName)
            favoriteModel.postValue(pokemons)
            isLoading.postValue(false)
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