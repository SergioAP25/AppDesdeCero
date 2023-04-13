package com.sergio.appdesdecero.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.sergio.appdesdecero.databinding.HomeActivityBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonHome.setOnClickListener {
            navigateToSearcBarActivity()
        }


    }

    private fun navigateToSearcBarActivity(){
        val intent = Intent(this, SearchBarActivity::class.java)
        startActivity(intent)
    }
}