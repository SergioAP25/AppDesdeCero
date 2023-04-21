package com.sergio.appdesdecero.ui.view.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.appdesdecero.databinding.FragmentSearchBinding
import com.sergio.appdesdecero.ui.view.DetailActivity
import com.sergio.appdesdecero.ui.view.recyclerview.PokemonAdapter
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter
    private var typeList: MutableList<String> = mutableListOf()
    private lateinit var buttonList: List<View>
    private var lastQuery: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
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
        loadButtonState()
        initButtons()
        adapter = PokemonAdapter(emptyList(), this::navigatetoDetail, this::addFavorite,
            this::removeFavorite, this::isFavorite)
        binding.rvPokemon.setHasFixedSize(true)
        binding.rvPokemon.layoutManager = LinearLayoutManager(context)
        binding.rvPokemon.adapter = adapter
        observer("")
        binding.searchbar.setOnQueryTextListener(object:
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

    private fun observer(query: String){
        pokemonViewModel.isLoading.observe(viewLifecycleOwner, Observer {isLoading ->
            if(isLoading){
                adapter.setData(emptyList())
                binding.bar.setVisibility(View.VISIBLE)
            }
            else{
                binding.bar.setVisibility(View.INVISIBLE)
            }
        })

        if(pokemonViewModel.scope!=null){
            pokemonViewModel.scope!!.cancel()
        }

        pokemonViewModel.pokemonSearch(query, typeList)
        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner, Observer {pokemon ->
            adapter.setData(pokemon)
        })
    }

    private fun initButtons(){
        binding.normal.setOnClickListener {
            if(!binding.normal.isSelected){
                if (countSelectedButtons()<2){
                    binding.normal.isSelected = true
                    binding.normal.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("normal")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.normal.isSelected = false
                binding.normal.background = null
                typeList.remove("normal")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.fire.setOnClickListener {
            if(!binding.fire.isSelected){
                if (countSelectedButtons()<2){
                    binding.fire.isSelected = true
                    binding.fire.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fire")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.fire.isSelected = false
                binding.fire.background = null
                typeList.remove("fire")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.water.setOnClickListener {
            if(!binding.water.isSelected){
                if (countSelectedButtons()<2){
                    binding.water.isSelected = true
                    binding.water.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("water")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.water.isSelected = false
                binding.water.background = null
                typeList.remove("water")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.grass.setOnClickListener {
            if(!binding.grass.isSelected){
                if (countSelectedButtons()<2){
                    binding.grass.isSelected = true
                    binding.grass.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("grass")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.grass.isSelected = false
                binding.grass.background = null
                typeList.remove("grass")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.electric.setOnClickListener {
            if(!binding.electric.isSelected){
                if (countSelectedButtons()<2){
                    binding.electric.isSelected = true
                    binding.electric.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("electric")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.electric.isSelected = false
                binding.electric.background = null
                typeList.remove("electric")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.ice.setOnClickListener {
            if(!binding.ice.isSelected){
                if (countSelectedButtons()<2){
                    binding.ice.isSelected = true
                    binding.ice.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ice")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.ice.isSelected = false
                binding.ice.background = null
                typeList.remove("ice")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)

            }
        }

        binding.ground.setOnClickListener {
            if(!binding.ground.isSelected){
                if (countSelectedButtons()<2){
                    binding.ground.isSelected = true
                    binding.ground.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ground")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.ground.isSelected = false
                binding.ground.background = null
                typeList.remove("ground")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.flying.setOnClickListener {
            if(!binding.flying.isSelected){
                if (countSelectedButtons()<2){
                    binding.flying.isSelected = true
                    binding.flying.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("flying")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.flying.isSelected = false
                binding.flying.background = null
                typeList.remove("flying")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.poison.setOnClickListener {
            if(!binding.poison.isSelected){
                if (countSelectedButtons()<2){
                    binding.poison.isSelected = true
                    binding.poison.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("poison")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.poison.isSelected = false
                binding.poison.background = null
                typeList.remove("poison")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.fighting.setOnClickListener {
            if(!binding.fighting.isSelected){
                if (countSelectedButtons()<2){
                    binding.fighting.isSelected = true
                    binding.fighting.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fighting")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.fighting.isSelected = false
                binding.fighting.background = null
                typeList.remove("fighting")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.psychic.setOnClickListener {
            if(!binding.psychic.isSelected){
                if (countSelectedButtons()<2){
                    binding.psychic.isSelected = true
                    binding.psychic.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("psychic")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.psychic.isSelected = false
                binding.psychic.background = null
                typeList.remove("psychic")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.dark.setOnClickListener {
            if(!binding.dark.isSelected){
                if (countSelectedButtons()<2){
                    binding.dark.isSelected = true
                    binding.dark.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dark")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.dark.isSelected = false
                binding.dark.background = null
                typeList.remove("dark")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.rock.setOnClickListener {
            if(!binding.rock.isSelected){
                if (countSelectedButtons()<2){
                    binding.rock.isSelected = true
                    binding.rock.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("rock")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.rock.isSelected = false
                binding.rock.background = null
                typeList.remove("rock")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.bug.setOnClickListener {
            if(!binding.bug.isSelected){
                if (countSelectedButtons()<2){
                    binding.bug.isSelected = true
                    binding.bug.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("bug")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.bug.isSelected = false
                binding.bug.background = null
                typeList.remove("bug")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.ghost.setOnClickListener {
            if(!binding.ghost.isSelected){
                if (countSelectedButtons()<2){
                    binding.ghost.isSelected = true
                    binding.ghost.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ghost")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.ghost.isSelected = false
                binding.ghost.background = null
                typeList.remove("ghost")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.steel.setOnClickListener {
            if(!binding.steel.isSelected){
                if (countSelectedButtons()<2){
                    binding.steel.isSelected = true
                    binding.steel.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("steel")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.steel.isSelected = false
                binding.steel.background = null
                typeList.remove("steel")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.dragon.setOnClickListener {
            if(!binding.dragon.isSelected){
                if (countSelectedButtons()<2){
                    binding.dragon.isSelected = true
                    binding.dragon.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dragon")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.dragon.isSelected = false
                binding.dragon.background = null
                typeList.remove("dragon")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
            }
        }

        binding.fairy.setOnClickListener {
            if(!binding.fairy.isSelected){
                if (countSelectedButtons()<2){
                    binding.fairy.isSelected = true
                    binding.fairy.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fairy")
                    pokemonViewModel.pokemonSearch(lastQuery, typeList)
                }
            }
            else{
                binding.fairy.isSelected = false
                binding.fairy.background = null
                typeList.remove("fairy")
                pokemonViewModel.pokemonSearch(lastQuery, typeList)
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
        for (i in 0 until buttonList.size - 1){
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