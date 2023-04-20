package com.sergio.appdesdecero.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sergio.appdesdecero.data.model.Species
import com.sergio.appdesdecero.data.model.Sprites
import com.sergio.appdesdecero.data.model.Stats
import com.sergio.appdesdecero.data.model.Types
import com.sergio.appdesdecero.domain.model.FilteredPokemon

const val DEFAULT_SPRITE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/132.png"

@Entity(tableName = "pokemon")
data class PokemonEntity (
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "species") val species: Species,
    @ColumnInfo(name = "sprites", defaultValue = DEFAULT_SPRITE) val sprites: Sprites,
    @ColumnInfo(name = "stats") val stats: List<Stats>,
    @ColumnInfo(name = "types") val types: List<Types>,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    )

fun FilteredPokemon.toDatabase() = PokemonEntity(name = name, species = species, sprites = sprites,
    stats = stats, types = types, height = height, weight = weight)