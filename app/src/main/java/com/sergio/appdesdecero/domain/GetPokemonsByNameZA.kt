package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsByNameZA @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(name: String): List<FilteredPokemon> {
        val pokemons = repository.getPokemonsFromDatabaseByNameZA(name)
        return pokemons
    }
}