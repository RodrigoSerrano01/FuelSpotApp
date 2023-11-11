package com.nf.fuelspot.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.nf.fuelspot.R
import com.nf.fuelspot.databinding.ActivityLoginBinding
import java.util.Objects

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authentication = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginRegisterButton = findViewById<Button>(R.id.login_registerButton)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)

        /**
         * Realiza autenticação e chama a função mainActivityRedirect em
         * caso de sucesso
         */
        binding.loginLoginButton.setOnClickListener {
            val email = binding.loginEmailLogin.text.toString()
            val senha = binding.loginPasswordLogin.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(
                    it, "Todos os campos precisam estar preenchidos!",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                authentication.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { auth ->
                        if (auth.isSuccessful) {
                            mainActivityRedirect()
                        }
                    }.addOnFailureListener { exception ->
                        val errorMessage = when (exception) {
                            is FirebaseAuthInvalidCredentialsException -> "E-mail inválido ou inexistente!"
                            is FirebaseNetworkException -> "Sem conexão com a Internet!"
                            else -> {
                                "Senha incorreta!"
                            }
                        }
                        val snackbar = Snackbar.make(
                            it, errorMessage, Snackbar.LENGTH_SHORT
                        )
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }
            }
        }

        loginRegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        HeaderActivity.createListener(registerButton, loginButton, textTittle, this);
    }

    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if (Objects.nonNull(usuarioAtual)) {
            mainActivityRedirect()
        }
    }

    /**
     * Redireciona para tela principal
     */
    private fun mainActivityRedirect() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}