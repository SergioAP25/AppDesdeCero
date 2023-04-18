package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.data.database.entities.toDatabase
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import java.util.*
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val repository: PokemonRepository
){
    private val pokemonList: MutableList<FilteredPokemon> = mutableListOf<FilteredPokemon>()
    suspend operator fun invoke(){
        addPokemonsToList()
        capitalize()
        repository.clearDatabase()
        repository.insertPokemons(pokemonList.map { it.toDatabase() })
    }

    private suspend fun addPokemonsToList(){
        repository.getAllPokemonsFromApi()?.results?.forEach{
            pokemonList.add(repository.getAllPokemonsByNameFromApi(it.url.substring(34))!!)
        }
    }

    private fun capitalize(){
        pokemonList.forEach {
            it.name = it.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }
}