package com.sergio.appdesdecero.domain

import com.sergio.appdesdecero.data.PokemonRepository
import javax.inject.Inject

class IsFavorite @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Boolean {
        return repository.isFavority(name)
    }
}