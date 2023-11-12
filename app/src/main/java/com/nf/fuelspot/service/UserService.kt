package com.nf.fuelspot.service

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nf.fuelspot.controller.UserController
import java.security.MessageDigest

class UserService {



    companion object {
        private val authentication = FirebaseAuth.getInstance()
        private val database = FirebaseFirestore.getInstance()
        fun userAddDataBase(user: UserController) {


            authentication.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())


            val usuariosMap = hashMapOf(
                "nome" to user.getUserName(),
                "e-mail" to user.getUserEmail(),
                "senha" to user.getUserPassword()
            )

            database.collection("Usuários")
                .document(user.getUserName()).set(usuariosMap)
                .addOnCompleteListener {
                    Log.d("database", "Dados salvos com sucesso!")
                }.addOnFailureListener {
                    Log.d("database", "Erro durante o salvamento dos dados!")
                }


        }
    }
    }


