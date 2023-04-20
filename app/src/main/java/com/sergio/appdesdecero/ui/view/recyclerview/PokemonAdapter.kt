package com.sergio.appdesdecero.ui.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import kotlin.reflect.KSuspendFunction1

class PokemonAdapter(
    var pokemonData: List<FilteredPokemon> = emptyList(),
    private val onItemSelected:(String)->Unit,
    private val addFavorite: KSuspendFunction1<String, Unit>,
    private val removeFavorite: KSuspendFunction1<String, Unit>,
    private val isFavorite: KSuspendFunction1<String, Boolean>
): RecyclerView.Adapter<PokemonViewHolder>(){

    fun setData(data: List<FilteredPokemon>) {
        pokemonData = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_cell,parent,false))
    }

    override fun onBindViewHolder(viewholder: PokemonViewHolder, position: Int) {
        val item = pokemonData
        viewholder.bind(pokemonData[position], onItemSelected, addFavorite, removeFavorite, isFavorite)
    }


    override fun getItemCount(): Int {
        return pokemonData.size
    }
}