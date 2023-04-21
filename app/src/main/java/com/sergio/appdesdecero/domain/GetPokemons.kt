package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.data.database.entities.toDatabase
import com.sergio.appdesdecero.data.model.PokemonResults
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import java.util.*
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val repository: PokemonRepository
){
    private var apiList: List<PokemonResults> = emptyList()
    suspend operator fun invoke(){
        apiList = repository.getAllPokemonsFromApi()?.results!!

        if(!apiList.isEmpty() && !repository.exists(capitalizeName(apiList.last().name))){
            capitalizeList()
            insertPokemonsIntoDatabase()
        }
    }

    private suspend fun insertPokemonsIntoDatabase(){
        var pokemon :FilteredPokemon?
        for(i in 0 until apiList.size){
            if(!repository.exists(apiList[i].name)){
                pokemon = repository.getAllPokemonsByNameFromApi(apiList[i].url.substring(34))!!
                repository.insertPokemons(pokemon.toDatabase())
            }
        }
    }

    private fun capitalizeName(name: String): String{
        return name.replaceFirstChar {
            if (it.isLowerCase()){
                it.titlecase(Locale.getDefault())
            }
            else {
                it.toString()
            }
        }
    }
    private fun capitalizeList(){
        apiList.forEach {
            it.name = capitalizeName(it.name)
        }
    }
}