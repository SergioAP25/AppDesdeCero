package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import java.util.logging.Filter
import javax.inject.Inject

class GetRandomPokemon @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): FilteredPokemon {
        return repository.getRandomPokemonFromDatabase()
    }
}