package com.nf.fuelspot.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.nf.fuelspot.R
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.nf.fuelspot.databinding.ActivityRegisterBinding
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val authentication = FirebaseAuth.getInstance()
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginLoginButton.setOnClickListener {
            val nome = binding.registerNome.text.toString()
            val email = binding.registerEmailLogin.text.toString()
            val senha = binding.registerPassword.text.toString()
            val senhaConfirmada = binding.registerConfirmPassword.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaConfirmada.isEmpty()) {
                val snackbar = Snackbar.make(
                    it, "Todos os campos precisam estar preenchidos!", Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else if (!senha.equals(senhaConfirmada)) {
                val snackbar = Snackbar.make(
                    it, "A senha deve ser a mesma em ambos os campos!", Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                authentication.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { registry ->
                        if (registry.isSuccessful) {
                            val snackbar = Snackbar.make(
                                it, "Cadastro completo!", Snackbar.LENGTH_SHORT
                            )
                            snackbar.setBackgroundTint(Color.GREEN)
                            snackbar.setTextColor(Color.BLACK)
                            snackbar.show()

                            val usuariosMap = hashMapOf(
                                "nome" to nome,
                                "e-mail" to email,
                                "senha" to encriptaSenha(senha)
                            )

                            database.collection("Usuários")
                                .document(nome).set(usuariosMap)
                                .addOnCompleteListener {
                                    Log.d("database", "Dados salvos com sucesso!")
                                }.addOnFailureListener {
                                    Log.d("database", "Erro durante o salvamento dos dados!")
                                }


                            binding.registerNome.setText("")
                            binding.registerEmailLogin.setText("")
                            binding.registerPassword.setText("")
                            binding.registerConfirmPassword.setText("")

                        }
                    }.addOnFailureListener { exception ->
                        val errorMessage = when (exception) {
                            is FirebaseAuthWeakPasswordException -> "Digite uma senha com no " +
                                    "mínimo seis dígitos!"

                            is FirebaseAuthInvalidCredentialsException -> "Digite um e-mail válido!"
                            is FirebaseAuthUserCollisionException -> "Este e-mail já possui uma " +
                                    "conta vinculada!"

                            is FirebaseNetworkException -> "Sem conexão com a Internet!"
                            else -> "Erro durante o cadastro de usuário!"
                        }
                        val snackbar = Snackbar.make(
                            it, errorMessage, Snackbar.LENGTH_SHORT
                        )
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                    }
            }
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
            // Crie um Intent para abrir a tela de cadastro (activity_register)
            val intent = Intent(this, RegisterSpotActivity::class.java)
            // Inicie a nova atividade
            startActivity(intent)
        }
        HeaderActivity.createListener(registerButton, loginButton, textTittle, this);
    }

    /**
     * Função para encriptar senha e salvar no banco
     */
    fun encriptaSenha(senha: String): String {
        val bytes = senha.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val senhaHash = digest.digest(bytes)
        return senhaHash.joinToString("") { "%02x".format(it) }
    }
}
