package com.sergio.appdesdecero.data.model

import com.google.gson.annotations.SerializedName

data class PokemonModel (
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonItemResponse
)

data class PokemonItemResponse (
    @SerializedName("front_default") val front_default: String,
)