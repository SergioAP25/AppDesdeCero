package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.data.database.entities.toDatabase
import com.sergio.appdesdecero.data.model.PokemonResults
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.PokemonDescription
import java.util.*
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val repository: PokemonRepository
){
    private var apiList: List<PokemonResults> = emptyList()
    suspend operator fun invoke(){
        apiList = repository.getAllPokemonsFromApi()?.results!!
        if(!apiList.isEmpty() && !repository.exists(capitalizeName(apiList.last().name))){
            insertPokemonsIntoDatabase()
        }
    }

    private suspend fun insertPokemonsIntoDatabase(){
        var pokemon :FilteredPokemon
        var pokemonDescription: PokemonDescription
        var startingIndex = repository.countPokemons()

        for(i in startingIndex until apiList.size){
            pokemon = repository.getAllPokemonsByNameFromApi(apiList[i].url.substring(34))!!
            pokemonDescription = repository.getPokemonDescriptionByNameFromApi(pokemon.species.url.substring(42))!!
            pokemon.name = capitalizeName(pokemon.name)
            repository.insertPokemon(pokemon.toDatabase())
            repository.insertPokemonDescription(repository.countPokemons(), pokemonDescription.descriptions)

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
}