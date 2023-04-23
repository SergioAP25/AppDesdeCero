package com.sergio.appdesdecero.data

import android.util.Log
import com.sergio.appdesdecero.data.database.dao.FavoritesDao
import com.sergio.appdesdecero.data.database.dao.PokemonDao
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.data.model.Types
import com.sergio.appdesdecero.data.network.PokemonService
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import com.sergio.appdesdecero.domain.model.toDomain



import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao,
    private val favoritesDao: FavoritesDao
) {
    suspend fun getAllPokemonsFromApi(): Pokemon?{
        val response: PokemonModel? = api.getPokemons()
        return response?.toDomain()
    }

    suspend fun getAllPokemonsByNameFromApi(name: String): FilteredPokemon?{
        val response: FilteredPokemonModel? = api.getPokemonsByName(name)
        return response?.toDomain()
    }

    suspend fun getPokemonsFromDatabaseByName(name: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByName(name)
        return response.map { it.toDomain() }
    }

    suspend fun getDetailPokemonFromDatabase(name: String): FilteredPokemon {
        val response = pokemonDao.getDetailPokemon(name)
        return response.toDomain()
    }

    suspend fun getPokemonsFromDatabaseByNameAZ(name: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameAZ(name)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameZA(name: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameZA(name)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameFilteredByType(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByType(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameFilteredByTypeAZ(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByTypeAZ(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameFilteredByTypeZA(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByTypeZA(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameFilteredByMultiType(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByMultiType(name, type1, type2)
        return response.map { it.toDomain() }
    }
    suspend fun getPokemonsFromDatabaseByNameFilteredByMultiTypeAZ(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByMultiTypeAZ(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDatabaseByNameFilteredByMultiTypeZA(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByNameFilteredByMultiTypeZA(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsByName(name: String): List<FilteredPokemon> {
        val response = pokemonDao.getFavoritePokemonByName(name)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByType(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByType(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByMultiType(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByMultiType(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun countPokemons(): Int{
        return pokemonDao.countPokemons()
    }

    suspend fun insertPokemon(pokemon : PokemonEntity){
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun exists(name: String): Boolean{
        return pokemonDao.exists(name)
    }

    suspend fun addFavorite(name: String){
        favoritesDao.addFavorite(name)
    }

    suspend fun removeFavorite(name: String){
        favoritesDao.removeFavorite(name)
    }

    suspend fun isFavority(name: String): Boolean{
        return favoritesDao.isFavorite(name)
    }
}