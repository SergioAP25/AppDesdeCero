package com.sergio.appdesdecero.ui.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sergio.appdesdecero.R
import com.sergio.appdesdecero.databinding.LoginActivityBinding
import java.security.Provider

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat_Light_NoActionBar)
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        session()
    }

    private fun initUI(){
        binding.register.setOnClickListener {
            if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            navigatetoHome(it.result.user?.email ?: "", ProviderType.BASIC)
                        }
                        else{
                            mensajeError("Ha habido un error con el registro, por favor, inténtelo de nuevo")
                        }
                    }
            }
        }

        binding.login.setOnClickListener {
            if (binding.email.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        navigatetoHome(it.result.user?.email ?: "", ProviderType.BASIC)
                    }
                    else{
                        mensajeError("Se ha producido un error en la autenticación del usuario")
                    }
                }
            }
        }

        binding.loginGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)

                if (account!=null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            navigatetoHome(account.email ?: "", ProviderType.GOOGLE)
                        }
                        else{
                            mensajeError("Se ha producido un error en la autenticación del usuario")
                        }
                    }
                }
            }
            catch (e: ApiException){
                mensajeError("Se ha producido un error en la autenticación del usuario")
            }
        }
    }

    private fun session(){
        val prefs = getSharedPreferences("PokeSearch", Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email!=null && provider!=null){
            navigatetoHome(email, ProviderType.valueOf(provider))
        }
    }

    private fun navigatetoHome(email: String, provider: ProviderType){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("provider", provider.name)
        startActivity(intent)
        finish()
    }

    private fun mensajeError(mensaje: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}