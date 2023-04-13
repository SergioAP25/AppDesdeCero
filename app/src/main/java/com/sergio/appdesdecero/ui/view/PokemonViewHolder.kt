package com.sergio.appdesdecero.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sergio.appdesdecero.databinding.PokemonCellBinding
import com.sergio.appdesdecero.domain.model.Pokemon

class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = PokemonCellBinding.bind(view)

   fun bind(pokemon: Pokemon){
       binding.pokemonname.text = pokemon.name
   }
}