package com.sergio.appdesdecero.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        initUI()
        setContentView(binding.root)
    }

    private fun initUI(){
        binding.register.setOnClickListener {
            if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){

                            navigatetoHome()
                        }
                        else{
                            Snackbar.make(binding.root, "Ha habido un error con el registro, por favor, inténtelo de nuevo",
                                Snackbar.LENGTH_LONG).show();
                        }
                    }
            }
        }

        binding.login.setOnClickListener {
            if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        navigatetoHome()
                    }
                    else{
                        Snackbar.make(binding.root, "Email o contraseña incorrectos",
                            Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private fun navigatetoHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}