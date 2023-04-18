package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import javax.inject.Inject

class GetDetailPokemon @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(name: String): FilteredPokemon {
        val pokemons = repository.getDetailPokemonFromDatabase(name)
        return pokemons
    }
}