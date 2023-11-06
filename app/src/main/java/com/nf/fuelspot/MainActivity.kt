package com.nf.fuelspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginRegisterButton = findViewById<Button>(R.id.login_registerButton)

        loginRegisterButton.setOnClickListener {
            // Crie um Intent para abrir a tela de cadastro (activity_register)
            val intent = Intent(this, RegisterActivity::class.java)

            // Inicie a nova atividade
            startActivity(intent)
        }
    }
}