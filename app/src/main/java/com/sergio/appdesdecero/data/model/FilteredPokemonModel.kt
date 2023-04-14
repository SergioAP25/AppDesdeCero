package com.sergio.appdesdecero.data.model

import com.google.gson.annotations.SerializedName

data class FilteredPokemonModel (
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites
)

data class Sprites (
    @SerializedName("front_default") val front_default: String
    )