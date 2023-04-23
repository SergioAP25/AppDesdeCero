package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetPokemonsByNameFilteredByTypeAZ @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String, type1: String): List<FilteredPokemon> {
        val pokemons = repository.getPokemonsFromDatabaseByNameFilteredByTypeAZ(name, type1)
        return pokemons
    }
}