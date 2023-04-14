package com.sergio.appdesdecero.ui.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sergio.appdesdecero.databinding.DetailActivityBinding
import com.sergio.appdesdecero.ui.viewmodel.PokemonDetailViewModel
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    lateinit var binding: DetailActivityBinding
    private val pokemonDetailViewModel: PokemonDetailViewModel by viewModels()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String = intent.getStringExtra(EXTRA_NAME).orEmpty()

        pokemonDetailViewModel.onCreate(name)
        pokemonDetailViewModel.pokemonModel.observe(context, Observer {pokemon ->
            Picasso.get().load(pokemon?.sprites?.front_default).into(binding.pokemonImage)
            binding.pokemonName.text = pokemon?.name
        })
    }
}