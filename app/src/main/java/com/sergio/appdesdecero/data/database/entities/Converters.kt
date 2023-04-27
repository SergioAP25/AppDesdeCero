package com.sergio.appdesdecero.data.database.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sergio.appdesdecero.data.model.*

class Converters {

    @TypeConverter
    fun fromSpecies(species: Species): String {
        return species.url
    }

    @TypeConverter
    fun toSpecies(url: String): Species{
        return Species(url)
    }

    @TypeConverter
    fun fromSprites(sprites: Sprites): String {
        return sprites.front_default
    }

    @TypeConverter
    fun toSprites(front_default: String): Sprites{
        return Sprites(front_default)
    }

    @TypeConverter
    fun fromStatsList(value: List<Stats>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Stats>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStatsList(value: String): List<Stats> {
        val gson = Gson()
        val type = object : TypeToken<List<Stats>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTypesList(value: List<Types>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Types>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTypesList(value: String): List<Types> {
        val gson = Gson()
        val type = object : TypeToken<List<Types>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDescriptionList(value: List<Description>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Description>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDescriptionList(value: String): List<Description> {
        val gson = Gson()
        val type = object : TypeToken<List<Description>>() {}.type
        return gson.fromJson(value, type)
    }
}