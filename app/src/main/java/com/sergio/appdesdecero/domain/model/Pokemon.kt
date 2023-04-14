package com.sergio.appdesdecero.domain.model
import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.data.model.PokemonResults

data class Pokemon(
    val results: List<PokemonResults>
)

fun PokemonModel.toDomain() = Pokemon(results)
