package com.sergio.appdesdecero.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sergio.appdesdecero.data.database.entities.FavoritesEntity
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.Types

@Dao
interface FavoritesDao {
    @Query("INSERT INTO favorites (pokemonName) VALUES(:name)")
    suspend fun addFavorite(name: String)

    @Query("DELETE FROM favorites WHERE pokemonName = :name")
    suspend fun removeFavorite(name: String)

    @Query("SELECT (SELECT COUNT(*) FROM favorites WHERE pokemonName = :name) == 1")
    suspend fun isFavorite(name: String): Boolean


}