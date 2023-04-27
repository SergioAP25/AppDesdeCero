package com.sergio.appdesdecero.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sergio.appdesdecero.data.model.Types

@Entity(tableName = "favorites")
data class FavoritesEntity (
    @PrimaryKey
    @ColumnInfo(name = "pokemonName") val name: String,
)