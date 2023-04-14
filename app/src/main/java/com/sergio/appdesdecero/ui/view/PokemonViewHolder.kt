package com.sergio.appdesdecero.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sergio.appdesdecero.databinding.PokemonCellBinding
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = PokemonCellBinding.bind(view)

   fun bind(pokemon: FilteredPokemon, onItemSelected: (String) -> Unit){
       Picasso.get().load(pokemon.sprites.front_default).into(binding.pokemonimage)
       binding.pokemonname.text = pokemon.name
       binding.root.setOnClickListener{
            onItemSelected(pokemon.name)
       }
   }
}