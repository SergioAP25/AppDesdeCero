package com.sergio.appdesdecero.data.network

import android.util.Log
import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val api: PokemonApiClient
) {
    suspend fun getPokemons(): PokemonModel?{
        return withContext(Dispatchers.IO){
            val response: Response<PokemonModel> = api.getAllPokemons()
            response.body()
        }
    }

    suspend fun getPokemonsByName(url: String): FilteredPokemonModel?{
        return withContext(Dispatchers.IO){
            val response: Response<FilteredPokemonModel> = api.getAllPokemonsByName(url)
            response.body()
        }
    }

    suspend fun getDetailPokemonByName(name: String): FilteredPokemonModel?{
        return withContext(Dispatchers.IO){
            val response: Response<FilteredPokemonModel> = api.getDetailPokemon(name)

            response.body()
        }
    }
}