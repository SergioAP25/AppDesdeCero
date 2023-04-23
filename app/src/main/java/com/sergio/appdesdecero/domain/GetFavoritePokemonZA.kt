package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetFavoritePokemonZA @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): List<FilteredPokemon> {
        val pokemons = repository.getFavoritePokemonsByNameZA(name)
        return pokemons
    }
}