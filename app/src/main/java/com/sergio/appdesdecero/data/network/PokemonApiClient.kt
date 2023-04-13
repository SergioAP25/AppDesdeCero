package com.sergio.appdesdecero.data.network

import com.sergio.appdesdecero.data.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {
    @GET("{name}")
    suspend fun getAllPokemons(@Path("name") pokemonName:String): Response<List<PokemonModel>>
}