package com.sergio.appdesdecero.data.network

import android.util.Log
import com.sergio.appdesdecero.data.model.DescriptionPokemonModel
import com.sergio.appdesdecero.data.model.FilteredPokemonModel
import com.sergio.appdesdecero.data.model.PokemonModel
import com.sergio.appdesdecero.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val api: PokemonApiClient
) {
    suspend fun getPokemons(): PokemonModel?{
        return try {
            withContext(Dispatchers.IO){
                val response: Response<PokemonModel> = api.getAllPokemons()
                response.body()
            }
        } catch (t: Throwable){
            PokemonModel(emptyList())
        }
    }

    suspend fun getPokemonsByName(name: String): FilteredPokemonModel?{
        var response: Response<FilteredPokemonModel>? = null
        return withContext(Dispatchers.IO){
            try{
                response = api.getAllPokemonsByName(name)
            }catch (t: Throwable){

            }
            response?.body()
        }
    }

    suspend fun getPokemonsDescriptionByName(name: String): DescriptionPokemonModel?{
        var response: Response<DescriptionPokemonModel>? = null
        return withContext(Dispatchers.IO){
            try{
                response = api.getPokemonDescriptionByName(name)
            }catch (t: Throwable){

            }
            response?.body()
        }
    }


}