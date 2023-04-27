package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.data.model.Description
import com.sergio.appdesdecero.data.model.Types
import com.sergio.appdesdecero.domain.*
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.PokemonDescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getDetailPokemon: GetDetailPokemon,
    private val getPokemonDescriptions: GetPokemonDescriptions,
    private val getRandomPokemon: GetRandomPokemon,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private  val isFavorite: IsFavorite
): ViewModel(){
    val pokemonModel = MutableLiveData<FilteredPokemon?>()
    val pokemonDescription = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>()
    var scope: Job? = null

    fun onCreate(pokemonName: String) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)

            val result = getDetailPokemon(pokemonName)

            pokemonModel.postValue(result)

            val description = getPokemonDescriptions(pokemonName).descriptions[0].flavor_text.replace("\n", " ")

            pokemonDescription.postValue(description)

            isLoading.postValue(false)
        }
    }

    fun randomPokemon() {
        scope = viewModelScope.launch {
            isLoading.postValue(true)

            val result = getRandomPokemon()

            pokemonModel.postValue(result)
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