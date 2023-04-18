package com.sergio.appdesdecero.domain.model

import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.*

data class FilteredPokemon(
    var name: String,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stats>,
    val types: List<Types>,
    val weight: Int
)

fun FilteredPokemonModel.toDomain() = FilteredPokemon(name, species, sprites,
    stats, types, weight)

fun PokemonEntity.toDomain() = FilteredPokemon(name, species, sprites, stats, types, weight)
