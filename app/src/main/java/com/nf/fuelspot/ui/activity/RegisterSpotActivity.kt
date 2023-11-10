package com.nf.fuelspot.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nf.fuelspot.R
import utils.ButtonActionsUtil

class RegisterSpotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_spot)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        registerButton.setOnClickListener {
            ButtonActionsUtil.handleRegisterButtonClick(this)
        }

        loginButton.setOnClickListener {
            ButtonActionsUtil.handleLoginButtonClick(this)
        }
    }
}