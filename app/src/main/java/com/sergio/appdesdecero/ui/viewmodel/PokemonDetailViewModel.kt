package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.domain.GetDetailPokemon
import com.sergio.appdesdecero.domain.GetPokemonsByName
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    val getDetailPokemon: GetDetailPokemon
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
}