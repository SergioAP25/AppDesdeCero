package com.sergio.appdesdecero.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sergio.appdesdecero.data.database.entities.FavoritesEntity
import com.sergio.appdesdecero.data.database.entities.PokemonEntity
import com.sergio.appdesdecero.data.model.Description
import com.sergio.appdesdecero.data.model.Types

@Dao
interface DescriptionsDao {
    @Query("INSERT INTO descriptions (descriptions_id, descriptions) VALUES(:id, :description)")
    suspend fun insertPokemonDescriptions(id: Int, description: List<Description>)

    @Query("SELECT COUNT(*) FROM descriptions")
    suspend fun countDescriptions(): Int

}