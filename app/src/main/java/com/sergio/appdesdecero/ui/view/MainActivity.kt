package com.sergio.appdesdecero.ui.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.databinding.HomeActivityBinding
import com.sergio.appdesdecero.ui.view.fragments.FavoriteFragment
import com.sergio.appdesdecero.ui.view.fragments.HomeFragment
import com.sergio.appdesdecero.ui.view.fragments.OptionsFragment
import com.sergio.appdesdecero.ui.view.fragments.SearchFragment
import com.sergio.appdesdecero.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

enum class ProviderType {
    BASIC,
    GOOGLE
}
@AndroidEntryPoint
class MainActivity @Inject constructor(
): AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private val favoriteFragment = FavoriteFragment()
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val optionsFragment = OptionsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        val prefs = getSharedPreferences("PokeSearch", Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)


        replaceFragment(favoriteFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.favoriteFragment -> replaceFragment(favoriteFragment)
                R.id.homeFragment -> replaceFragment(homeFragment)
                R.id.searchFragment -> replaceFragment(searchFragment)
                R.id.optionsFragment -> replaceFragment(optionsFragment)
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        if(pokemonViewModel.updateScope!=null){
            pokemonViewModel.updateScope!!.cancel()
        }

        pokemonViewModel.updateDatabase()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame_layout, fragment)
        transaction.commit()
    }
}