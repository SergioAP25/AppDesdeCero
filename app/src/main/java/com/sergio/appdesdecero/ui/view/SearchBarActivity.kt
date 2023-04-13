package com.sergio.appdesdecero.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergio.appdesdecero.databinding.HomeActivityBinding
import com.sergio.appdesdecero.databinding.SearchBarActivityBinding

class SearchBarActivity: AppCompatActivity() {
    private lateinit var binding: SearchBarActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchBarActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}