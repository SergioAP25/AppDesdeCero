package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.data.model.PokemonResults
import com.sergio.appdesdecero.domain.*
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemons: GetPokemons,
    private val getPokemonsByName: GetPokemonsByName,
    private val getFavoritePokemon: GetFavoritePokemon,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private val isFavorite: IsFavorite
): ViewModel() {
    var pokemonModel = MutableLiveData<List<FilteredPokemon>>()
    var favoriteModel = MutableLiveData<List<FilteredPokemon>>()
    val isLoading = MutableLiveData<Boolean>()
    var scope: Job? = null


    fun onCreate(pokemonName: String) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            getPokemons()
            val pokemons = getPokemonsByName(pokemonName)
            Log.v("MENSAJE", pokemonName)
            Log.v("MENSAJE", pokemons.toString())
            Log.v("MENSAJE", "====================")
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }

    }

    fun favorite(pokemonName: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val pokemons = getFavoritePokemon(pokemonName)
            favoriteModel.postValue(pokemons)
            isLoading.postValue(false)
        }
    }

    suspend fun stop(){

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