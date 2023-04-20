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
    private val pokemonList: MutableList<FilteredPokemon> = mutableListOf<FilteredPokemon>()
    private var apiList: List<PokemonResults> = emptyList()
    suspend operator fun invoke(){
        apiList = repository.getAllPokemonsFromApi()?.results!!

        if(!apiList.isEmpty() && !repository.exists(capitalizeName(apiList.last().name))){
            addPokemonsToList()
            capitalizeList()
            repository.insertPokemons(pokemonList.map { it.toDatabase() })
        }
    }

    private suspend fun addPokemonsToList(){
        for (i in apiList.lastIndex downTo 0) {
           if(!repository.exists(apiList[i].name)){
               pokemonList.add(repository.getAllPokemonsByNameFromApi(apiList[i].url.substring(34))!!)
           }
           else{
                break
           }
        }
        pokemonList.reverse()
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
        pokemonList.forEach {
            it.name = it.name.replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase(Locale.ROOT)
                }
                else {
                    it.toString()
                }
            }
        }
    }
}