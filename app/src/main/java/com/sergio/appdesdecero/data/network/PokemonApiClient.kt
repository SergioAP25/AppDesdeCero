package com.sergio.appdesdecero.data.network

import com.sergio.appdesdecero.data.model.DescriptionPokemonModel
import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {
    @GET("pokemon?limit=100000")
    suspend fun getAllPokemons(): Response<PokemonModel>

    @GET("pokemon/{name}")
    suspend fun getAllPokemonsByName(@Path("name") url: String): Response<FilteredPokemonModel>

    @GET("pokemon-species/{name}")
    suspend fun getPokemonDescriptionByName(@Path("name") url: String): Response<DescriptionPokemonModel>
}