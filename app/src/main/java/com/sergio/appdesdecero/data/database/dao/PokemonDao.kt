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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemons()

    @Query("SELECT (SELECT COUNT(*) FROM pokemon) == 0")
    fun isEmpty(): Boolean

}