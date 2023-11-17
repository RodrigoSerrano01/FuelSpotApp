package com.nf.fuelspot.service

import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.nf.fuelspot.controller.UserController

class OwnerService {

    companion object {
        private val database = FirebaseFirestore.getInstance()
        fun ownerAddDataBase(user: UserController, senhaConfirmada: String, it: View): Boolean {
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
                val proprietariosMap = hashMapOf(
                    "nome" to user.getUserName(),
                    "e-mail" to user.getUserEmail(),
                    "senha" to user.getUserPassword(),
                    "userId" to user.getUserId(),
                )

                database.collection("Proprietários")
                    .document(user.getUserName()).set(proprietariosMap)
                    .addOnCompleteListener {
                        Log.d("database", "Dados salvos com sucesso!")
                    }.addOnFailureListener {
                        Log.d("database", "Erro durante o salvamento dos dados!")
                    }
                return true
            }
            return false
        }
    }
}
