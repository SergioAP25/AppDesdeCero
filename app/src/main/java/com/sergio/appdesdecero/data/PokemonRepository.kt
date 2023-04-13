package com.sergio.appdesdecero.data

import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.data.network.PokemonService
import com.sergio.appdesdecero.domain.model.Pokemon
import com.sergio.appdesdecero.domain.model.toDomain



import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService
) {
    suspend fun getAllPokemonsFromApi(pokemonName: String): List<Pokemon>{
        val response: List<PokemonModel> = api.getPokemons(pokemonName)
        return response.map { it.toDomain() }
    }
}