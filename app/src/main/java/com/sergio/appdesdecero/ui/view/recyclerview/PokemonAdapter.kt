package com.sergio.appdesdecero.ui.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.domain.model.FilteredPokemon

class PokemonAdapter(
    var pokemonData: List<FilteredPokemon> = emptyList(),
    private val onItemSelected:(String)->Unit
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
        viewholder.bind(pokemonData[position], onItemSelected)
    }


    override fun getItemCount(): Int {
        return pokemonData.size
    }
}