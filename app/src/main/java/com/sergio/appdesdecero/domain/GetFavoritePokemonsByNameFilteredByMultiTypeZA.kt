package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetFavoritePokemonsByNameFilteredByMultiTypeZA @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String, type1: String, type2: String): List<FilteredPokemon> {
        val pokemons = repository.getFavoritePokemonsFromDatabaseByNameFilteredByMultiTypeZA(name, type1, type2)
        return pokemons
    }
}