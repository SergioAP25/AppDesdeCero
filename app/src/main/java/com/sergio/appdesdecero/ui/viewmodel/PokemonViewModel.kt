package com.sergio.appdesdecero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.appdesdecero.data.model.PokemonResults
import com.sergio.appdesdecero.domain.GetPokemons
import com.sergio.appdesdecero.domain.GetPokemonsByName
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val getPokemons: GetPokemons,
    val getPokemonsByName: GetPokemonsByName
): ViewModel() {
    val pokemonModel = MutableLiveData<MutableList<FilteredPokemon>?>()
    val isLoading = MutableLiveData<Boolean>()
    var filteredPokemon: MutableList<FilteredPokemon> = mutableListOf<FilteredPokemon>()

    fun onCreate(pokemonName: String) {
        filteredPokemon.clear()
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getPokemons()?.results?.filter {
                it.name.contains(pokemonName) }

            getFilteredPokemon(result)

            if(result!=null){
                pokemonModel.postValue(filteredPokemon)
                isLoading.postValue(false)
            }
        }
    }

    suspend fun getFilteredPokemon(list: List<PokemonResults>?){
        var aux: FilteredPokemon
        list?.forEach {
            aux = getPokemonsByName(it.url.substring(34))!!
            if(!filteredPokemon.contains(aux)){
                filteredPokemon.add(aux)
            }
        }
    }
}