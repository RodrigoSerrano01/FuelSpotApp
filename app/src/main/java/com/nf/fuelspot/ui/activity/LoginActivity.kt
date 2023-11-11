package com.nf.fuelspot.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.nf.fuelspot.R
import utils.ButtonActionsUtil

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginRegisterButton = findViewById<Button>(R.id.login_registerButton)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)

        loginRegisterButton.setOnClickListener {
             val intent = Intent(this, RegisterActivity::class.java)

             startActivity(intent)
        }



        HeaderActivity.createListener(registerButton,loginButton,textTittle,this);
    }
}