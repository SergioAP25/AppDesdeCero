package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.PokemonDescription
import javax.inject.Inject

class GetPokemonDescriptions @Inject constructor(
    private val repository: PokemonRepository
){
    suspend operator fun invoke(name: String): PokemonDescription {
        val pokemonDescriptions = repository.getPokemonDescriptionsFromDatabase(name)
        return pokemonDescriptions
    }
}