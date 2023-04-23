package com.sergio.appdesdecero.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergio.appdesdecero.data.database.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%'")
    suspend fun getPokemonByName(name: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name = :name")
    suspend fun getDetailPokemon(name: String):PokemonEntity

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND name LIKE '%'||:name||'%'")
    suspend fun getFavoritePokemonByName(name: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND name LIKE '%'||:name||'%' ORDER BY p.name ASC")
    suspend fun getFavoritePokemonByNameAZ(name: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND name LIKE '%'||:name||'%' ORDER BY p.name DESC")
    suspend fun getFavoritePokemonByNameZA(name: String):List<PokemonEntity>

    @Query("SELECT (SELECT COUNT(*) FROM pokemon WHERE name = :name) == 1")
    suspend fun exists(name: String): Boolean

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' ORDER BY name ASC")
    suspend fun getPokemonByNameAZ(name: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' ORDER BY name DESC")
    suspend fun getPokemonByNameZA(name: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%'")
    suspend fun getPokemonByNameFilteredByType(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%' ORDER BY name ASC")
    suspend fun getPokemonByNameFilteredByTypeAZ(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%' ORDER BY name DESC")
    suspend fun getPokemonByNameFilteredByTypeZA(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%' AND types LIKE '%'||:type2||'%'")
    suspend fun getPokemonByNameFilteredByMultiType(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%' AND types LIKE '%'||:type2||'%' ORDER BY name ASC")
    suspend fun getPokemonByNameFilteredByMultiTypeAZ(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE name LIKE '%'||:name||'%' AND types LIKE '%'||:type1||'%' AND types LIKE '%'||:type2||'%' ORDER BY name DESC")
    suspend fun getPokemonByNameFilteredByMultiTypeZA(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%'")
    suspend fun getFavoritePokemonByNameFilteredByType(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%' ORDER BY p.name ASC")
    suspend fun getFavoritePokemonByNameFilteredByTypeAZ(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%' ORDER BY p.name DESC")
    suspend fun getFavoritePokemonByNameFilteredByTypeZA(name: String, type1: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%' AND p.types LIKE '%'||:type2||'%'")
    suspend fun getFavoritePokemonByNameFilteredByMultiType(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%' AND p.types LIKE '%'||:type2||'%' ORDER BY p.name ASC")
    suspend fun getFavoritePokemonByNameFilteredByMultiTypeAZ(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT * FROM pokemon p, favorites f WHERE p.name = f.pokemonName AND p.name LIKE '%'||:name||'%' AND p.types LIKE '%'||:type1||'%' AND p.types LIKE '%'||:type2||'%' ORDER BY p.name DESC")
    suspend fun getFavoritePokemonByNameFilteredByMultiTypeZA(name: String, type1: String, type2: String):List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun countPokemons(): Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon : PokemonEntity)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()

    @Query("SELECT (SELECT COUNT(*) FROM pokemon) == 0")
    fun isEmpty(): Boolean

}