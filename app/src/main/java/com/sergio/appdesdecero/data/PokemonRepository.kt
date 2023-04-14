package com.sergio.appdesdecero.data

import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.data.network.PokemonService
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import com.sergio.appdesdecero.domain.model.toDomain



import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService
) {
    suspend fun getAllPokemonsFromApi(): Pokemon?{
        val response: PokemonModel? = api.getPokemons()
        return response?.toDomain()
    }

    suspend fun getAllPokemonsByNameFromApi(url: String): FilteredPokemon?{
        val response: FilteredPokemonModel? = api.getPokemonsByName(url)
        return response?.toDomain()
    }

    suspend fun getDetailPokemonFromApi(name: String): FilteredPokemon?{
        val response: FilteredPokemonModel? = api.getDetailPokemonByName(name)
        return response?.toDomain()
    }
}