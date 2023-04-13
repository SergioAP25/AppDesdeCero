package com.sergio.appdesdecero.data.network

import android.util.Log
import com.sergio.appdesdecero.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val api: PokemonApiClient
) {
    suspend fun getPokemons(pokemonName: String): List<PokemonModel>{
        return withContext(Dispatchers.IO){
            val response: Response<List<PokemonModel>> = api.getAllPokemons(pokemonName)
            Log.v("UI", response.body()?.size.toString())
            response.body() ?: emptyList()
        }
    }
}