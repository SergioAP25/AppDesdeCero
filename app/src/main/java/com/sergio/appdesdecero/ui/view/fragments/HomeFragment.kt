package com.sergio.appdesdecero.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.appdesdecero.databinding.FragmentHomeBinding
import com.sergio.appdesdecero.ui.view.DetailActivity
import com.sergio.appdesdecero.ui.view.recyclerview.PokemonAdapter
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        initUI()
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun initUI(){
        configSwipe()
        adapter = PokemonAdapter(emptyList(), this::navigatetoDetail, this::addFavorite,
            this::removeFavorite, this::isFavorite)
        binding.rvFavorites.setHasFixedSize(true)
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        binding.rvFavorites.adapter = adapter
        observer("")
        binding.favoritesSearchbar.setOnQueryTextListener(object:
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                observer(query.orEmpty())
                return false
            }
        })
    }

    private fun configSwipe(){
        binding.favoritesSwipe.setOnRefreshListener {
            parentFragmentManager.beginTransaction()
                .detach(this).commit()
            parentFragmentManager.beginTransaction()
                .attach(this).commit()
            binding.favoritesSwipe.isRefreshing = false
        }
    }

    private fun observer(query: String){
        pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading ->
            if(isLoading){
                adapter.setData(emptyList())
                binding.favoritesProgessbar.setVisibility(View.VISIBLE)
            }
            else{
                binding.favoritesProgessbar.setVisibility(View.GONE)
            }
        })

        if(pokemonViewModel.scope!=null){
            pokemonViewModel.scope!!.cancel()
        }

        pokemonViewModel.favoritePokemonSearch(query)
        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner, Observer {pokemon ->
            adapter.setData(pokemon)
        })
    }

    private fun navigatetoDetail(name:String){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_NAME, name)
        startActivity(intent)
    }
    private suspend fun addFavorite(name: String){
        pokemonViewModel.addFavoritePokemon(name)
    }

    private suspend fun removeFavorite(name: String){
        pokemonViewModel.removeFavoritePokemon(name)
    }

    private suspend fun isFavorite(name: String): Boolean{
        return pokemonViewModel.isFavoritePokemon(name)
    }

}