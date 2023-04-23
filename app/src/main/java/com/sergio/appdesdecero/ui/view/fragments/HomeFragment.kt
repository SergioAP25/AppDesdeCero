package com.sergio.appdesdecero.ui.view.fragments

import android.content.Intent
import android.graphics.Color
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
import com.sergio.appdesdecero.domain.model.FilteredPokemon
import com.sergio.appdesdecero.ui.view.DetailActivity
import com.sergio.appdesdecero.ui.view.recyclerview.PokemonAdapter
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter
    private var typeList: MutableList<String> = mutableListOf()
    private lateinit var buttonList: List<View>
    private var lastQuery: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        buttonList = listOf(binding.normal, binding.fire, binding.water, binding.grass,
            binding.electric, binding.ice, binding.ground, binding.flying,
            binding.poison, binding.fighting, binding.psychic, binding.dark,
            binding.rock, binding.bug, binding.ghost, binding.steel, binding.dragon, binding.fairy)

        initUI()
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun initUI(){
        configSwipe()
        loadButtonState()
        initButtons()
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
                lastQuery = query.orEmpty()
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

        pokemonViewModel.favoritePokemonSearch(query, typeList)
        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner, Observer {pokemon ->
            adapter.setData(pokemon)
            manageVisibility(pokemon)
        })
    }

    private fun manageVisibility(pokemon: List<FilteredPokemon>) {
        if (pokemon.isEmpty()){
            binding.rvFavorites.setVisibility(View.GONE)
            binding.notFound.setVisibility(View.VISIBLE)
        }
        else{
            binding.rvFavorites.setVisibility(View.VISIBLE)
            binding.notFound.setVisibility(View.GONE)
        }
    }

    private fun initButtons(){
        binding.normal.setOnClickListener {
            if(!binding.normal.isSelected){
                if (countSelectedButtons()<2){
                    binding.normal.isSelected = true
                    binding.normal.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("normal")
                    observer(lastQuery)
                }
            }
            else{
                binding.normal.isSelected = false
                binding.normal.background = null
                typeList.remove("normal")
                observer(lastQuery)
            }
        }

        binding.fire.setOnClickListener {
            if(!binding.fire.isSelected){
                if (countSelectedButtons()<2){
                    binding.fire.isSelected = true
                    binding.fire.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fire")
                    observer(lastQuery)
                }
            }
            else{
                binding.fire.isSelected = false
                binding.fire.background = null
                typeList.remove("fire")
                observer(lastQuery)
            }
        }

        binding.water.setOnClickListener {
            if(!binding.water.isSelected){
                if (countSelectedButtons()<2){
                    binding.water.isSelected = true
                    binding.water.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("water")
                    observer(lastQuery)
                }
            }
            else{
                binding.water.isSelected = false
                binding.water.background = null
                typeList.remove("water")
                observer(lastQuery)
            }
        }

        binding.grass.setOnClickListener {
            if(!binding.grass.isSelected){
                if (countSelectedButtons()<2){
                    binding.grass.isSelected = true
                    binding.grass.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("grass")
                    observer(lastQuery)
                }
            }
            else{
                binding.grass.isSelected = false
                binding.grass.background = null
                typeList.remove("grass")
                observer(lastQuery)
            }
        }

        binding.electric.setOnClickListener {
            if(!binding.electric.isSelected){
                if (countSelectedButtons()<2){
                    binding.electric.isSelected = true
                    binding.electric.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("electric")
                    observer(lastQuery)
                }
            }
            else{
                binding.electric.isSelected = false
                binding.electric.background = null
                typeList.remove("electric")
                observer(lastQuery)
            }
        }

        binding.ice.setOnClickListener {
            if(!binding.ice.isSelected){
                if (countSelectedButtons()<2){
                    binding.ice.isSelected = true
                    binding.ice.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ice")
                    observer(lastQuery)
                }
            }
            else{
                binding.ice.isSelected = false
                binding.ice.background = null
                typeList.remove("ice")
                observer(lastQuery)
            }
        }

        binding.ground.setOnClickListener {
            if(!binding.ground.isSelected){
                if (countSelectedButtons()<2){
                    binding.ground.isSelected = true
                    binding.ground.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ground")
                    observer(lastQuery)
                }
            }
            else{
                binding.ground.isSelected = false
                binding.ground.background = null
                typeList.remove("ground")
                observer(lastQuery)
            }
        }

        binding.flying.setOnClickListener {
            if(!binding.flying.isSelected){
                if (countSelectedButtons()<2){
                    binding.flying.isSelected = true
                    binding.flying.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("flying")
                    observer(lastQuery)
                }
            }
            else{
                binding.flying.isSelected = false
                binding.flying.background = null
                typeList.remove("flying")
                observer(lastQuery)
            }
        }

        binding.poison.setOnClickListener {
            if(!binding.poison.isSelected){
                if (countSelectedButtons()<2){
                    binding.poison.isSelected = true
                    binding.poison.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("poison")
                    observer(lastQuery)
                }
            }
            else{
                binding.poison.isSelected = false
                binding.poison.background = null
                typeList.remove("poison")
                observer(lastQuery)
            }
        }

        binding.fighting.setOnClickListener {
            if(!binding.fighting.isSelected){
                if (countSelectedButtons()<2){
                    binding.fighting.isSelected = true
                    binding.fighting.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fighting")
                    observer(lastQuery)
                }
            }
            else{
                binding.fighting.isSelected = false
                binding.fighting.background = null
                typeList.remove("fighting")
                observer(lastQuery)
            }
        }

        binding.psychic.setOnClickListener {
            if(!binding.psychic.isSelected){
                if (countSelectedButtons()<2){
                    binding.psychic.isSelected = true
                    binding.psychic.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("psychic")
                    observer(lastQuery)
                }
            }
            else{
                binding.psychic.isSelected = false
                binding.psychic.background = null
                typeList.remove("psychic")
                observer(lastQuery)
            }
        }

        binding.dark.setOnClickListener {
            if(!binding.dark.isSelected){
                if (countSelectedButtons()<2){
                    binding.dark.isSelected = true
                    binding.dark.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dark")
                    observer(lastQuery)
                }
            }
            else{
                binding.dark.isSelected = false
                binding.dark.background = null
                typeList.remove("dark")
                observer(lastQuery)
            }
        }

        binding.rock.setOnClickListener {
            if(!binding.rock.isSelected){
                if (countSelectedButtons()<2){
                    binding.rock.isSelected = true
                    binding.rock.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("rock")
                    observer(lastQuery)
                }
            }
            else{
                binding.rock.isSelected = false
                binding.rock.background = null
                typeList.remove("rock")
                observer(lastQuery)
            }
        }

        binding.bug.setOnClickListener {
            if(!binding.bug.isSelected){
                if (countSelectedButtons()<2){
                    binding.bug.isSelected = true
                    binding.bug.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("bug")
                    observer(lastQuery)
                }
            }
            else{
                binding.bug.isSelected = false
                binding.bug.background = null
                typeList.remove("bug")
                observer(lastQuery)
            }
        }

        binding.ghost.setOnClickListener {
            if(!binding.ghost.isSelected){
                if (countSelectedButtons()<2){
                    binding.ghost.isSelected = true
                    binding.ghost.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ghost")
                    observer(lastQuery)
                }
            }
            else{
                binding.ghost.isSelected = false
                binding.ghost.background = null
                typeList.remove("ghost")
                observer(lastQuery)
            }
        }

        binding.steel.setOnClickListener {
            if(!binding.steel.isSelected){
                if (countSelectedButtons()<2){
                    binding.steel.isSelected = true
                    binding.steel.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("steel")
                    observer(lastQuery)
                }
            }
            else{
                binding.steel.isSelected = false
                binding.steel.background = null
                typeList.remove("steel")
                observer(lastQuery)
            }
        }

        binding.dragon.setOnClickListener {
            if(!binding.dragon.isSelected){
                if (countSelectedButtons()<2){
                    binding.dragon.isSelected = true
                    binding.dragon.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dragon")
                    observer(lastQuery)
                }
            }
            else{
                binding.dragon.isSelected = false
                binding.dragon.background = null
                typeList.remove("dragon")
                observer(lastQuery)
            }
        }

        binding.fairy.setOnClickListener {
            if(!binding.fairy.isSelected){
                if (countSelectedButtons()<2){
                    binding.fairy.isSelected = true
                    binding.fairy.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fairy")
                    observer(lastQuery)
                }
            }
            else{
                binding.fairy.isSelected = false
                binding.fairy.background = null
                typeList.remove("fairy")
                observer(lastQuery)
            }
        }
    }

    private fun loadButtonState(){
        typeList.forEach {
            when(it){
                "normal" -> {
                    binding.normal.isSelected = true
                    binding.normal.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "fire" -> {
                    binding.fire.isSelected = true
                    binding.fire.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "water" -> {
                    binding.water.isSelected = true
                    binding.water.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "grass" -> {
                    binding.grass.isSelected = true
                    binding.grass.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "electric" -> {
                    binding.electric.isSelected = true
                    binding.electric.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "ice" -> {
                    binding.ice.isSelected = true
                    binding.ice.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "ground" -> {
                    binding.ground.isSelected = true
                    binding.ground.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "flying" -> {
                    binding.flying.isSelected = true
                    binding.flying.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "poison" -> {
                    binding.poison.isSelected = true
                    binding.poison.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "fighting" -> {
                    binding.fighting.isSelected = true
                    binding.fighting.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "psychic" -> {
                    binding.psychic.isSelected = true
                    binding.psychic.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "dark" -> {
                    binding.dark.isSelected = true
                    binding.dark.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "rock" -> {
                    binding.rock.isSelected = true
                    binding.rock.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "bug" -> {
                    binding.bug.isSelected = true
                    binding.bug.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "ghost" -> {
                    binding.ghost.isSelected = true
                    binding.ghost.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "steel" -> {
                    binding.steel.isSelected = true
                    binding.steel.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "dragon" -> {
                    binding.dragon.isSelected =true
                    binding.dragon.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
                "fairy" -> {
                    binding.fairy.isSelected =true
                    binding.fairy.setBackgroundColor(Color.parseColor("#DAD3D3"))
                }
            }
        }
    }
    private fun countSelectedButtons(): Int{
        var number = 0
        for (i in 0 until buttonList.size){
            if (buttonList[i].isSelected){
                number++
            }
        }
        return number
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