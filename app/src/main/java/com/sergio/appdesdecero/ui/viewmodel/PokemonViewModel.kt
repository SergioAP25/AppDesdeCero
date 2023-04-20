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
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val getPokemons: GetPokemons,
    val getPokemonsByName: GetPokemonsByName,
    val addFavorite: AddFavorite,
    val removeFavorite: RemoveFavorite,
    val isFavorite: IsFavorite
): ViewModel() {
    var pokemonModel = MutableLiveData<List<FilteredPokemon>>()
    val isLoading = MutableLiveData<Boolean>()


    fun onCreate(pokemonName: String) {
        viewModelScope.launch {
            var time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            isLoading.postValue(true)
            if(time in 24..24){
                getPokemons()
            }
            val pokemons = getPokemonsByName(pokemonName)
            pokemonModel.postValue(pokemons)
            isLoading.postValue(false)
        }
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