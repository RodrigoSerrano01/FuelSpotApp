package com.nf.fuelspot.service

import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.nf.fuelspot.controller.UserController

class UserService {

    companion object {
        private val authentication = FirebaseAuth.getInstance()
        private val database = FirebaseFirestore.getInstance()

        fun userAddDataBase(user: UserController, senhaConfirmada: String, it: View) {
            if (user.getUserName().isEmpty() || user.getUserEmail().isEmpty() ||
                user.getUserPassword().isEmpty() || senhaConfirmada.isEmpty()
            ) {
                val snackbar = Snackbar.make(
                    it, "Todos os campos precisam estar preenchidos!", Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else if (!user.getUserPassword().equals(user.encriptaSenha(senhaConfirmada))) {
                val snackbar = Snackbar.make(
                    it, "A senha deve ser a mesma em ambos os campos!", Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                authentication.createUserWithEmailAndPassword(
                    user.getUserEmail(),
                    user.getUserPassword()
                ).addOnCompleteListener { registry ->
                    if (registry.isSuccessful) {
                        val snackbar = Snackbar.make(
                            it, "Cadastro completo!", Snackbar.LENGTH_SHORT
                        )
                        snackbar.setBackgroundTint(Color.GREEN)
                        snackbar.setTextColor(Color.BLACK)
                        snackbar.show()

                        val usuariosMap = hashMapOf(
                            "nome" to user.getUserName(),
                            "e-mail" to user.getUserEmail(),
                            "senha" to user.getUserPassword(),
                            "userId" to user.getUserId()
                        )

                        database.collection("Usuários")
                            .document(user.getUserName()).set(usuariosMap)
                            .addOnCompleteListener {
                                Log.d("database", "Dados salvos com sucesso!")
                            }.addOnFailureListener {
                                Log.d("database", "Erro durante o salvamento dos dados!")
                            }
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
    }
}


