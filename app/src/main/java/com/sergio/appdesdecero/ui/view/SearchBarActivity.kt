package com.sergio.appdesdecero.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.appdesdecero.databinding.SearchBarActivityBinding
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBarActivity: AppCompatActivity() {
    private lateinit var binding: SearchBarActivityBinding
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchBarActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI(){
        adapter = PokemonAdapter()
        binding.rvPokemon.setHasFixedSize(true)
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = adapter
        binding.searchbar.setOnQueryTextListener(object:
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                pokemonViewModel.onCreate(query.orEmpty())
                pokemonViewModel.pokemonModel.observe(context, Observer {pokemon ->
                    adapter.setData(pokemon)
                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}