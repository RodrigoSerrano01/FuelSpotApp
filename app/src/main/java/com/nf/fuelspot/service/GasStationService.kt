package com.nf.fuelspot.service

import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.nf.fuelspot.controller.PostoController

class GasStationService {

    companion object {

        private val database = FirebaseFirestore.getInstance()

        fun gasAddDatabase(posto: PostoController, it: View, userName: String?) {
            if (posto.getPostoName().isEmpty() || posto.getPostoCnpj().isEmpty() ||
                posto.getPostoCep().isEmpty() || posto.getPostoBairro().isEmpty() ||
                posto.getPostoRua().isEmpty() || posto.getPostoNumero().isEmpty() ||
                posto.getPostoCidade().isEmpty()
            ) {
                val snackbar = Snackbar.make(
                    it, "Todos os campos precisam estar preenchidos!", Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                val postoMap = hashMapOf(
                    "nome" to posto.getPostoName(),
                    "cnpj" to posto.getPostoCnpj(),
                    "cep" to posto.getPostoCep(),
                    "bairro" to posto.getPostoBairro(),
                    "rua" to posto.getPostoRua(),
                    "numero" to posto.getPostoNumero(),
                    "cidade" to posto.getPostoCidade(),
                    "postoId" to posto.getPostoId(),
                )

                database.collection("Postos")
                    .document(posto.getPostoName()).set(postoMap)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d("database", "Dados da tabela 'Postos' salvos com sucesso!")
                            if (userName != null) {
                                database.collection("Proprietários")
                                    .document(userName)
                                    .update("postos", FieldValue.arrayUnion(postoMap))
                                    .addOnSuccessListener {
                                        Log.d(
                                            "database",
                                            "Objeto 'Posto' adicionado ao documento do usuário com sucesso!"
                                        )
                                    }
                                    .addOnFailureListener {
                                        Log.d(
                                            "database",
                                            "Erro ao adicionar objeto 'Posto' ao documento do usuário!"
                                        )
                                    }
                            }
                        } else {
                            Log.d(
                                "database",
                                "Erro durante o salvamento dos dados da tabela 'Postos'!"
                            )
                        }
                    }
            }
        }
    }
}
