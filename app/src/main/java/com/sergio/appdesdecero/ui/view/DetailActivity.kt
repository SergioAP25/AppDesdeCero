package com.sergio.appdesdecero.ui.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sergio.appdesdecero.databinding.DetailActivityBinding
import com.sergio.appdesdecero.ui.viewmodel.PokemonDetailViewModel
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

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

            updateStatBar(binding.hp, pokemon?.stats?.get(0)?.base_stat)
            updateStatBar(binding.attack, pokemon?.stats?.get(1)?.base_stat)
            updateStatBar(binding.defense, pokemon?.stats?.get(2)?.base_stat)
            updateStatBar(binding.specialAttack, pokemon?.stats?.get(3)?.base_stat)
            updateStatBar(binding.specialDefense, pokemon?.stats?.get(4)?.base_stat)
            updateStatBar(binding.speed, pokemon?.stats?.get(5)?.base_stat)

            if (pokemon?.types?.size==1){
                binding.type1.text = pokemon?.types?.get(0)?.type?.name
            }
            else{
                binding.type1.text = pokemon?.types?.get(0)?.type?.name
                binding.type2.text = pokemon?.types?.get(1)?.type?.name
            }

            binding.height1.text = (pokemon?.height?.toFloat()?.div(10)).toString()+" m"
            binding.weight1.text = (pokemon?.weight?.toFloat()?.div(10)).toString()+" kg"

        })
    }


    private fun updateStatBar(view: View, stat: Int?){
        val params = view.layoutParams
        params.height = pxToDp(stat?.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px :Float?): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px!!, resources.displayMetrics)
            .roundToInt()
    }
}