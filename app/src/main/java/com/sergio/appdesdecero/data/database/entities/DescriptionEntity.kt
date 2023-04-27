package com.sergio.appdesdecero.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sergio.appdesdecero.data.model.Description
import com.sergio.appdesdecero.data.model.Types

@Entity(tableName = "descriptions",
    foreignKeys = [ForeignKey(entity = PokemonEntity::class, parentColumns = ["id"],
        childColumns = ["descriptions_id"])])
data class DescriptionEntity (
    @PrimaryKey
    @ColumnInfo(name = "descriptions_id") val id: String,
    @ColumnInfo(name = "descriptions") val descriptions: List<Description>
)