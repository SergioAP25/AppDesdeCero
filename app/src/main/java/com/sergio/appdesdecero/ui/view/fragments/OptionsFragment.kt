package com.sergio.appdesdecero.ui.view.fragments

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.databinding.FragmentOptionsBinding
import com.sergio.appdesdecero.ui.view.LoginActivity
import com.sergio.appdesdecero.ui.view.MainActivity
import com.sergio.appdesdecero.ui.view.ProviderType
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(layoutInflater)
        val email = activity?.intent?.extras?.getString("email", null)
        val provider = activity?.intent?.extras?.getString("provider", null)
        initUI(email ?: "", provider ?: "")

        return binding.root
    }

    private fun initUI(email: String, provider: String){
        binding.email.text = email
        binding.provider.text = provider
        binding.logOutButton.setOnClickListener {
            val prefs = getActivity()?.getSharedPreferences("PokeSearch", Context.MODE_PRIVATE)?.edit()
            prefs?.clear()
            prefs?.apply()
            FirebaseAuth.getInstance().signOut()
            navigateToLogin()
        }
    }

    private fun navigateToLogin(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        getActivity()?.finish()
    }
}