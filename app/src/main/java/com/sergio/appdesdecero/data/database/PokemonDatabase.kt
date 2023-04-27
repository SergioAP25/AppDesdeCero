package com.sergio.appdesdecero.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sergio.appdesdecero.data.database.dao.DescriptionsDao
import com.sergio.appdesdecero.data.database.dao.FavoritesDao
import com.sergio.appdesdecero.data.database.dao.PokemonDao
import com.sergio.appdesdecero.data.database.entities.Converters
import com.sergio.appdesdecero.data.database.entities.DescriptionEntity
import com.sergio.appdesdecero.data.database.entities.FavoritesEntity
import com.sergio.appdesdecero.data.database.entities.PokemonEntity


@Database(entities = [PokemonEntity::class, FavoritesEntity::class, DescriptionEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDatabase: RoomDatabase(){
    abstract fun getPokemonDao(): PokemonDao

    abstract fun getFavoritesDao(): FavoritesDao

    abstract fun getDescriptionsDao(): DescriptionsDao

}