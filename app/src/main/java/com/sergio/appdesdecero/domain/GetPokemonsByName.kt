package com.sergio.appdesdecero.domain

import android.util.Log
import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonsByName @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(name: String): FilteredPokemon? {
        val pokemons = repository.getAllPokemonsByNameFromApi(name)
        return pokemons
    }
}