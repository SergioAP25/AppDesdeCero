package com.sergio.appdesdecero.domain.model

import com.sergio.appdesdecero.data.model.PokemonItemResponse
import com.sergio.appdesdecero.data.model.PokemonModel

data class Pokemon(
    val name: String,
    val sprites: PokemonItemResponse,
)

fun PokemonModel.toDomain() = Pokemon(name, sprites)
