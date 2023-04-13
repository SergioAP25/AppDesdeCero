package com.sergio.appdesdecero.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergio.appdesdecero.R

class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.pokemon_cell,parent,false))
    }

    override fun onBindViewHolder(viewholder: PokemonViewHolder, position: Int) {
        val item = pokemonList[position]
        viewholder.bind()
    }


    override fun getItemCount(): Int {
        return pokemonList.size
    }
}