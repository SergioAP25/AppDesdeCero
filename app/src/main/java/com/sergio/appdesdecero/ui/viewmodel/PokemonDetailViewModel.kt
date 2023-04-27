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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    var randomScope: Job? = null
    var pokemonName: String = ""

    fun onCreate(pokemonName: String) {
        scope = viewModelScope.launch {
            isLoading.postValue(true)

            val result = getDetailPokemon(pokemonName)

            pokemonModel.postValue(result)

            var description = ""
            var list = getPokemonDescriptions(pokemonName).descriptions
            if(!list.isEmpty()){
                for(i in 0 until list.size){
                    if (list[i].language.name.equals("en")){
                        description = getPokemonDescriptions(pokemonName).descriptions[i].flavor_text.replace("\n", " ")
                        break
                    }
                }
            }
            else{
                description = "This pokemon has no description known"
            }

            pokemonDescription.postValue(description)

            isLoading.postValue(false)
        }
    }

    fun homeFragmentCreate(){
        scope = viewModelScope.launch {
            isLoading.postValue(true)
            randomPokemon()
            randomScope?.join()
            val result = getDetailPokemon(pokemonName)

            pokemonModel.postValue(result)

            var description = ""
            var list = getPokemonDescriptions(pokemonName).descriptions
            if(!list.isEmpty()){
                for(i in 0 until list.size){
                    if (list[i].language.name.equals("en")){
                        description = getPokemonDescriptions(pokemonName).descriptions[i].flavor_text.replace("\n", " ")
                        break
                    }
                }
            }
            else{
                description = "This pokemon has no description known"
            }

            pokemonDescription.postValue(description)

            isLoading.postValue(false)
        }
    }

    fun randomPokemon(){
        randomScope = viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRandomPokemon()
            pokemonName = result.name
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