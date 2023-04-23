package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetFavoritePokemonsByNameFilteredByType @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String, type1: String): List<FilteredPokemon> {
        val pokemons = repository.getFavoritePokemonsFromDatabaseByNameFilteredByType(name, type1)
        return pokemons
    }
}