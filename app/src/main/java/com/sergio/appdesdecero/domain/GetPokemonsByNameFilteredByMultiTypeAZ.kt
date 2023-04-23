package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetPokemonsByNameFilteredByMultiTypeAZ @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String, type1: String, type2: String): List<FilteredPokemon> {
        val pokemons = repository.getPokemonsFromDatabaseByNameFilteredByMultiTypeAZ(name, type1, type2)
        return pokemons
    }
}