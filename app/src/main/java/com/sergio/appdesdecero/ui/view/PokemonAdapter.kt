package com.sergio.appdesdecero.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.domain.model.Pokemon

class PokemonAdapter(
    var pokemonData: List<Pokemon> = emptyList()
): RecyclerView.Adapter<PokemonViewHolder>(){

    fun setData(data: List<Pokemon>) {
        pokemonData = data
        Log.v("UI", "ADIOS")
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_cell,parent,false))
    }

    override fun onBindViewHolder(viewholder: PokemonViewHolder, position: Int) {
        val item = pokemonData
        viewholder.bind(pokemonData[position])
    }


    override fun getItemCount(): Int {
        return pokemonData.size
    }
}