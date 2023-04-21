package com.sergio.appdesdecero.ui.view.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.appdesdecero.R
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
    private lateinit var buttonList: List<View>
    private var typeList: MutableList<String> = mutableListOf()
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

        initButtons()
        initUI()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }


    private fun initUI(){
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

        pokemonViewModel.onCreate(query)
        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner, Observer {pokemon ->
            adapter.setData(pokemon)
        })
    }

    private fun filteredSearch(query: String){
        when(typeList.size){
            0 -> observer(query)
        }
    }

    private fun initButtons(){
        binding.normal.setOnClickListener {
            if(!binding.normal.isSelected){
                if (countSelectedButtons()<2){
                    binding.normal.isSelected = true
                    binding.normal.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("normal")
                }
            }
            else{
                binding.normal.isSelected = false
                binding.normal.background = null
                typeList.remove("normal")
            }
        }

        binding.fire.setOnClickListener {
            if(!binding.fire.isSelected){
                if (countSelectedButtons()<2){
                    binding.fire.isSelected = true
                    binding.fire.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fire")
                }
            }
            else{
                binding.fire.isSelected = false
                binding.fire.background = null
                typeList.remove("fire")
            }
        }

        binding.water.setOnClickListener {
            if(!binding.water.isSelected){
                if (countSelectedButtons()<2){
                    binding.water.isSelected = true
                    binding.water.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("water")
                }
            }
            else{
                binding.water.isSelected = false
                binding.water.background = null
                typeList.remove("water")
            }
        }

        binding.grass.setOnClickListener {
            if(!binding.grass.isSelected){
                if (countSelectedButtons()<2){
                    binding.grass.isSelected = true
                    binding.grass.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("grass")

                }
            }
            else{
                binding.grass.isSelected = false
                binding.grass.background = null
                typeList.remove("grass")
            }
        }

        binding.electric.setOnClickListener {
            if(!binding.electric.isSelected){
                if (countSelectedButtons()<2){
                    binding.electric.isSelected = true
                    binding.electric.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("electric")
                }
            }
            else{
                binding.electric.isSelected = false
                binding.electric.background = null
                typeList.remove("electric")
            }
        }

        binding.ice.setOnClickListener {
            if(!binding.ice.isSelected){
                if (countSelectedButtons()<2){
                    binding.ice.isSelected = true
                    binding.ice.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ice")
                }
            }
            else{
                binding.ice.isSelected = false
                binding.ice.background = null
                typeList.remove("ice")

            }
        }

        binding.ground.setOnClickListener {
            if(!binding.ground.isSelected){
                if (countSelectedButtons()<2){
                    binding.ground.isSelected = true
                    binding.ground.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ground")
                }
            }
            else{
                binding.ground.isSelected = false
                binding.ground.background = null
                typeList.remove("ground")
            }
        }

        binding.flying.setOnClickListener {
            if(!binding.flying.isSelected){
                if (countSelectedButtons()<2){
                    binding.flying.isSelected = true
                    binding.flying.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("flying")
                }
            }
            else{
                binding.flying.isSelected = false
                binding.flying.background = null
                typeList.remove("flying")
            }
        }

        binding.poison.setOnClickListener {
            if(!binding.poison.isSelected){
                if (countSelectedButtons()<2){
                    binding.poison.isSelected = true
                    binding.poison.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("poison")
                }
            }
            else{
                binding.poison.isSelected = false
                binding.poison.background = null
                typeList.remove("poison")
            }
        }

        binding.fighting.setOnClickListener {
            if(!binding.fighting.isSelected){
                if (countSelectedButtons()<2){
                    binding.fighting.isSelected = true
                    binding.fighting.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fighting")
                }
            }
            else{
                binding.fighting.isSelected = false
                binding.fighting.background = null
                typeList.remove("fighting")
            }
        }

        binding.psychic.setOnClickListener {
            if(!binding.psychic.isSelected){
                if (countSelectedButtons()<2){
                    binding.psychic.isSelected = true
                    binding.psychic.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("psychic")
                }
            }
            else{
                binding.psychic.isSelected = false
                binding.psychic.background = null
                typeList.remove("psychic")
            }
        }

        binding.dark.setOnClickListener {
            if(!binding.dark.isSelected){
                if (countSelectedButtons()<2){
                    binding.dark.isSelected = true
                    binding.dark.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dark")
                }
            }
            else{
                binding.dark.isSelected = false
                binding.dark.background = null
                typeList.remove("dark")
            }
        }

        binding.rock.setOnClickListener {
            if(!binding.rock.isSelected){
                if (countSelectedButtons()<2){
                    binding.rock.isSelected = true
                    binding.rock.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("rock")
                }
            }
            else{
                binding.rock.isSelected = false
                binding.rock.background = null
                typeList.remove("rock")
            }
        }

        binding.bug.setOnClickListener {
            if(!binding.bug.isSelected){
                if (countSelectedButtons()<2){
                    binding.bug.isSelected = true
                    binding.bug.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("bug")
                }
            }
            else{
                binding.bug.isSelected = false
                binding.bug.background = null
                typeList.remove("bug")
            }
        }

        binding.ghost.setOnClickListener {
            if(!binding.ghost.isSelected){
                if (countSelectedButtons()<2){
                    binding.ghost.isSelected = true
                    binding.ghost.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("ghost")
                }
            }
            else{
                binding.ghost.isSelected = false
                binding.ghost.background = null
                typeList.remove("ghost")
            }
        }

        binding.steel.setOnClickListener {
            if(!binding.steel.isSelected){
                if (countSelectedButtons()<2){
                    binding.steel.isSelected = true
                    binding.steel.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("steel")
                }
            }
            else{
                binding.steel.isSelected = false
                binding.steel.background = null
                typeList.remove("steel")
            }
        }

        binding.dragon.setOnClickListener {
            if(!binding.dragon.isSelected){
                if (countSelectedButtons()<2){
                    binding.dragon.isSelected = true
                    binding.dragon.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("dragon")
                }
            }
            else{
                binding.dragon.isSelected = false
                binding.dragon.background = null
                typeList.remove("dragon")
            }
        }

        binding.fairy.setOnClickListener {
            if(!binding.fairy.isSelected){
                if (countSelectedButtons()<2){
                    binding.fairy.isSelected = true
                    binding.fairy.setBackgroundColor(Color.parseColor("#DAD3D3"))
                    typeList.add("fairy")
                }
            }
            else{
                binding.fairy.isSelected = false
                binding.fairy.background = null
                typeList.remove("fairy")
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