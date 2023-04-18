package com.sergio.appdesdecero.data

import android.util.Log
import com.sergio.appdesdecero.data.database.dao.PokemonDao
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.data.network.PokemonService
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import com.sergio.appdesdecero.domain.model.toDomain



import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao
) {
    suspend fun getAllPokemonsFromApi(): Pokemon?{
        val response: PokemonModel? = api.getPokemons()
        return response?.toDomain()
    }

    suspend fun getAllPokemonsByNameFromApi(name: String): FilteredPokemon?{
        val response: FilteredPokemonModel? = api.getPokemonsByName(name)
        return response?.toDomain()
    }

    suspend fun getPokemonsFromDatabaseByName(name: String):List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByName(name)
        return response.map { it.toDomain() }
    }

    suspend fun getDetailPokemonFromDatabase(name: String): FilteredPokemon {
        val response = pokemonDao.getDetailPokemon(name)
        return response.toDomain()
    }

    suspend fun getFavoritePokemonFromDatabase(name: String): List<FilteredPokemon> {
        val response = pokemonDao.getFavoritePokemon(name)
        return response.map{ it.toDomain() }
    }

    suspend fun insertPokemons(pokemon :List<PokemonEntity>){
        pokemonDao.insertAll(pokemon)
    }

    suspend fun clearDatabase(){
        pokemonDao.deleteAllQuotes()
    }

    suspend fun isDatabaseEmpty(): Boolean{
        return pokemonDao.isEmpty()
    }


}