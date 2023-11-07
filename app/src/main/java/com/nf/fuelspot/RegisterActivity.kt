package com.nf.fuelspot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nf.fuelspot.R
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import utils.ButtonActionsUtil

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val checkboxMeat = findViewById<CheckBox>(R.id.checkbox_meat)
        val addPostoText = findViewById<TextView>(R.id.register_addPostoText)
        val addPostoButton = findViewById<TextView>(R.id.register_addPostoButton)
        val confirmButton = findViewById<AppCompatButton>(R.id.login_loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        checkboxMeat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addPostoText.visibility = View.VISIBLE
                addPostoButton.visibility = View.VISIBLE

                val params = confirmButton.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = R.id.register_addPostoButton
                params.topMargin = resources.getDimensionPixelSize(R.dimen.confirm_button_margin_checked)
                confirmButton.layoutParams = params
            } else {
                addPostoText.visibility = View.GONE
                addPostoButton.visibility = View.GONE
                val params = confirmButton.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = R.id.checkbox_meat
                params.topMargin = resources.getDimensionPixelSize(R.dimen.confirm_button_margin_unchecked)
                confirmButton.layoutParams = params
            }
        }

        val addSpotButton = findViewById<TextView>(R.id.register_addPostoButton)

        addSpotButton.setOnClickListener {
            // Crie um Intent para abrir a tela de cadastro (activity_register)
            val intent = Intent(this, RegisterSpotActivity::class.java)

            // Inicie a nova atividade
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            ButtonActionsUtil.handleRegisterButtonClick(this)
        }

        loginButton.setOnClickListener {
            ButtonActionsUtil.handleLoginButtonClick(this)
        }

    }
}
