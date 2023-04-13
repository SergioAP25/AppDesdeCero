package com.sergio.appdesdecero.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.sergio.appdesdecero.databinding.HomeActivityBinding
import com.sergio.appdesdecero.databinding.SearchBarActivityBinding

class SearchBarActivity: AppCompatActivity() {
    private lateinit var binding: SearchBarActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchBarActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI(){
        binding.searchbar.setOnQueryTextListener(object:
            SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}