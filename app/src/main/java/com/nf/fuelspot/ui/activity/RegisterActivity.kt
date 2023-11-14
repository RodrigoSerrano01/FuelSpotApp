package com.nf.fuelspot.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.UserController
import com.nf.fuelspot.databinding.ActivityRegisterBinding
import com.nf.fuelspot.service.UserService

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginLoginButton.setOnClickListener {

            val nome = binding.registerNome.text.toString()
            val email = binding.registerEmailLogin.text.toString()
            val senha = binding.registerPassword.text.toString()
            val senhaConfirmada = binding.registerConfirmPassword.text.toString()

            val user = UserController()
            user.createUser(nome, email, senha)
            UserService.userAddDataBase(user, senhaConfirmada, it)
        }

        val checkboxMeat = findViewById<CheckBox>(R.id.checkbox_meat)
        val addPostoText = findViewById<TextView>(R.id.register_addPostoText)
        val addPostoButton = findViewById<TextView>(R.id.register_addPostoButton)
        val confirmButton = findViewById<AppCompatButton>(R.id.login_loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)

        checkboxMeat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                addPostoText.visibility = View.VISIBLE
                addPostoButton.visibility = View.VISIBLE

                val params = confirmButton.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = R.id.register_addPostoButton
                params.topMargin =
                    resources.getDimensionPixelSize(R.dimen.confirm_button_margin_checked)
                confirmButton.layoutParams = params
            } else {
                addPostoText.visibility = View.GONE
                addPostoButton.visibility = View.GONE
                val params = confirmButton.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = R.id.checkbox_meat
                params.topMargin =
                    resources.getDimensionPixelSize(R.dimen.confirm_button_margin_unchecked)
                confirmButton.layoutParams = params
            }
        }

        val addSpotButton = findViewById<TextView>(R.id.register_addPostoButton)

        addSpotButton.setOnClickListener {

            val nome = binding.registerNome.text.toString()
            val email = binding.registerEmailLogin.text.toString()
            val senha = binding.registerPassword.text.toString()
            val senhaConfirmada = binding.registerConfirmPassword.text.toString()

            val user = UserController()
            user.createUser(nome, email, senha)
            UserService.userAddDataBase(user, senhaConfirmada, it)
            if (user.getUserName().isNotEmpty() || user.getUserEmail().isNotEmpty() ||
                user.getUserPassword().isNotEmpty() || senhaConfirmada.isNotEmpty()
            ) {
                val intent = Intent(this, RegisterSpotActivity::class.java)
                startActivity(intent)
            }
        }
        HeaderActivity.createListener(registerButton, loginButton, textTittle, this);
    }
}