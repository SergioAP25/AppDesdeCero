package com.sergio.appdesdecero.domain.model

import com.sergio.appdesdecero.data.database.entities.DescriptionEntity
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.*

data class PokemonDescription(
    val descriptions: List<Description>
)

fun DescriptionPokemonModel.toDomain() = PokemonDescription(flavor_text_entries)

fun DescriptionEntity.toDomain() = PokemonDescription(descriptions)
