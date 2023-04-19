package com.sergio.appdesdecero.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.appdesdecero.databinding.SearchBarActivityBinding
import com.sergio.appdesdecero.ui.view.DetailActivity.Companion.EXTRA_NAME
import com.sergio.appdesdecero.ui.view.recyclerview.PokemonAdapter
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
        adapter = PokemonAdapter{name->navigatetoDetail(name)}
        binding.rvPokemon.setHasFixedSize(true)
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = adapter
        binding.searchbar.setOnQueryTextListener(object:
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                pokemonViewModel.onCreate(query.orEmpty())
                pokemonViewModel.pokemonModel.observe(context, Observer {pokemon ->
                    adapter.setData(pokemon)
                })
                return false
            }
        })
    }

    private fun navigatetoDetail(name:String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_NAME, name)
        startActivity(intent)
    }
}