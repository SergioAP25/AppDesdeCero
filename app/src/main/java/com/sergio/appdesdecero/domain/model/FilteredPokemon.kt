package com.sergio.appdesdecero.domain.model

import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonResults
import com.sergio.appdesdecero.data.model.Sprites

data class FilteredPokemon(
    val name: String,
    val sprites: Sprites
)

fun FilteredPokemonModel.toDomain() = FilteredPokemon(name, sprites)
