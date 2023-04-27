package com.sergio.appdesdecero.data

import android.util.Log
import com.sergio.appdesdecero.data.database.dao.DescriptionsDao
import com.sergio.appdesdecero.data.database.dao.FavoritesDao
import com.sergio.appdesdecero.data.database.dao.PokemonDao
import com.sergio.appdesdecero.data.database.entities.DescriptionEntity
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.*
import com.sergio.appdesdecero.data.network.PokemonService
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.domain.model.Pokemon
import com.sergio.appdesdecero.domain.model.PokemonDescription
import com.sergio.appdesdecero.domain.model.toDomain



import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao,
    private val favoritesDao: FavoritesDao,
    private val descriptionsDao: DescriptionsDao
) {
    private val name: String = "Bulbasaur"
    private val species: Species = Species("https://pokeapi.co/api/v2/pokemon-species/1/")
    private val sprites: Sprites =  Sprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
    private val stats: List<Stats> = listOf(Stats(45), Stats(49), Stats(49), Stats(65), Stats(65), Stats(45))
    private val types: List<Types> = listOf(Types(1, Type("grass")), Types(2, Type("poison")))
    private val height: Int = 7
    private val weight: Int = 69
    private val defaultPokemon: PokemonEntity = PokemonEntity(1, name, species, sprites, stats, types, height, weight)
    private val descriptionList: List<Description> = listOf(Description(
        "For some time after its birth, it grows by gaining nourishment from the seed on its back.",
        Language("en")))
    private val defaultDescriptions: DescriptionEntity = DescriptionEntity(1, descriptionList)


    suspend fun getAllPokemonsFromApi(): Pokemon?{
        val response: PokemonModel? = api.getPokemons()
        return response?.toDomain()
    }

    suspend fun getAllPokemonsByNameFromApi(name: String): FilteredPokemon?{
        val response: FilteredPokemonModel? = api.getPokemonsByName(name)
        return response?.toDomain()
    }

    suspend fun getPokemonDescriptionByNameFromApi(name: String): PokemonDescription? {
        val response: DescriptionPokemonModel? = api.getPokemonsDescriptionByName(name)
        return response?.toDomain()
    }

    suspend fun getPokemonsFromDatabaseByName(name: String): List<FilteredPokemon>{
        val response = pokemonDao.getPokemonByName(name)
        return response.map { it.toDomain() }
    }

    suspend fun getDetailPokemonFromDatabase(name: String): FilteredPokemon {
        var response = pokemonDao.getDetailPokemon(name)
        if (response==null){
            response = defaultPokemon
        }
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

    suspend fun getFavoritePokemonsByNameAZ(name: String): List<FilteredPokemon> {
        val response = pokemonDao.getFavoritePokemonByNameAZ(name)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsByNameZA(name: String): List<FilteredPokemon> {
        val response = pokemonDao.getFavoritePokemonByNameZA(name)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByType(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByType(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByTypeAZ(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByTypeAZ(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByTypeZA(name: String, type1: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByTypeZA(name, type1)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByMultiType(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByMultiType(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByMultiTypeAZ(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByMultiTypeAZ(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun getFavoritePokemonsFromDatabaseByNameFilteredByMultiTypeZA(name: String, type1: String, type2: String): List<FilteredPokemon>{
        val response = pokemonDao.getFavoritePokemonByNameFilteredByMultiTypeZA(name, type1, type2)
        return response.map { it.toDomain() }
    }

    suspend fun getRandomPokemonFromDatabase(): FilteredPokemon{
        var response = pokemonDao.getRandomPokemon()
        if(response==null){
            response = defaultPokemon
        }
        return response.toDomain()
    }

    suspend fun getPokemonDescriptionsFromDatabase(name: String): PokemonDescription {
        var response = pokemonDao.getPokemonDescriptions(name)
        if (response==null){
            response = defaultDescriptions
        }
        return response.toDomain()
    }

    suspend fun countPokemons(): Int{
        return pokemonDao.countPokemons()
    }

    suspend fun insertPokemon(pokemon : PokemonEntity){
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun insertPokemonDescription(id:Int, descriptions : List<Description>){
        descriptionsDao.insertPokemonDescriptions(id, descriptions)
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