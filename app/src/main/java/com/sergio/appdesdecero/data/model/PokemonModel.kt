package com.sergio.appdesdecero.data.model

import com.google.gson.annotations.SerializedName

data class PokemonModel (
    @SerializedName("results") val results: List<PokemonResults>
    )

data class PokemonResults(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
