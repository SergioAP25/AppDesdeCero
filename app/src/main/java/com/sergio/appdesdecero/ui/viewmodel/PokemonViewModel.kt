package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.domain.GetPokemons
import com.sergio.appdesdecero.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val getPokemons: GetPokemons
): ViewModel() {
    val pokemonModel = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(pokemonName: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPokemons(pokemonName)
            if(!result.isNullOrEmpty()){
                pokemonModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}