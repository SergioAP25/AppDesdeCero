package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import javax.inject.Inject

class Exists @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Boolean {
        return repository.exists(name)
    }
}