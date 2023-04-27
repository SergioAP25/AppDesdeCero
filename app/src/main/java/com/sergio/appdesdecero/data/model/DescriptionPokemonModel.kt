package com.sergio.appdesdecero.data.model

import com.google.gson.annotations.SerializedName

data class DescriptionPokemonModel (
    @SerializedName("flavor_text_entries") val flavor_text_entries: List<Description>
)

data class Description (
    @SerializedName("flavor_text") val flavor_text: String
)