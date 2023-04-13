package com.sergio.appdesdecero.data.model

import com.google.gson.annotations.SerializedName

data class PokemonModel (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)