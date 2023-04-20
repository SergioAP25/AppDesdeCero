package com.sergio.appdesdecero.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "favorites",
    foreignKeys = [ForeignKey(entity = PokemonEntity::class, parentColumns = ["name"],
        childColumns = ["pokemonName"])])
data class FavoritesEntity (
    @PrimaryKey
    @ColumnInfo(name = "pokemonName") val name: String
)