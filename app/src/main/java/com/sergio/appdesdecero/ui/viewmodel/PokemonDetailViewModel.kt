package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.domain.*
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getDetailPokemon: GetDetailPokemon,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private  val isFavorite: IsFavorite
): ViewModel(){
    val pokemonModel = MutableLiveData<FilteredPokemon?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(pokemonName: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getDetailPokemon(pokemonName)

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