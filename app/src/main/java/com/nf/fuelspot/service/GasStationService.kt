package com.nf.fuelspot.service

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nf.fuelspot.controller.GasStationController
import com.nf.fuelspot.controller.PostoController
import java.math.BigDecimal
import java.util.Objects

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
                if (posto.getPostoPrice().toDouble() <= 0.0) {
                    val zeroBigDecimal = BigDecimal("0.0")
                    posto.setPostoValorPrice(zeroBigDecimal)
                    val snackbar = Snackbar.make(
                        it, "Gasolina recebeu o valor de R$0,00!", Snackbar.LENGTH_SHORT
                    )
                    snackbar.setBackgroundTint(Color.YELLOW)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
                val postoMap = hashMapOf(
                    "nome" to posto.getPostoName(),
                    "cnpj" to posto.getPostoCnpj(),
                    "cep" to posto.getPostoCep(),
                    "bairro" to posto.getPostoBairro(),
                    "rua" to posto.getPostoRua(),
                    "numero" to posto.getPostoNumero(),
                    "cidade" to posto.getPostoCidade(),
                    "postoId" to posto.getPostoId(),
                    "valor" to posto.getPostoPrice().toString()
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

//    fun gasConsult(context: Context): MutableList<GasStationController> {
//        val gasList: MutableList<GasStationController> = mutableListOf<GasStationController>()
//
//        //= mutableListOf<GasStationController>()
//        val db = Firebase.firestore
//
//
//        db.collection("Postos")
//        .get()
//        .addOnSuccessListener { result ->
//            for (document in result) {
//                val gasAux  = GasStationController()
////                 Log.d(TAG, "!!!${document.id} => ${document.get("cidade")}")
//                val aux: String = ("${document.get("cidade")}, ${document.get("numero")}, ${document.get("rua")},${document.get("cep")},${document.get("bairro")} ")
//                gasAux.createGasStation(context,document.get("nome").toString(),
//                    BigDecimal("10"), BigDecimal("10"),aux, BigDecimal("10"), BigDecimal("10"))
//                gasList.add(gasAux)
//                //Log.d(ContentValues.TAG, "!!!${gasList.get(0).getGasName()}}")
//
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
//            Toast.makeText(context, "Não funcionou", Toast.LENGTH_SHORT).show()
//        }
//        Log.d(ContentValues.TAG, "!!!${gasList.size}")
//
//        return gasList
//    }

        fun gasConsult(context: Context, callback: (MutableList<GasStationController>) -> Unit) {
            val gasList: MutableList<GasStationController> = mutableListOf()
            val db = Firebase.firestore

            db.collection("Postos")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val aux: String =
                            ("${document.get("cidade")}, ${document.get("numero")}, ${document.get("rua")},${
                                document.get("cep")
                            },${document.get("bairro")} ")

                        val gasAux = GasStationController()
                        val valorAux = BigDecimal(document.get("valor").toString())
                        Log.d(TAG, "!!!!!!!! ${valorAux}")
                        gasAux.createGasStation(
                            context,
                            document.get("nome").toString(),
                            valorAux,
                            BigDecimal("5"),
                            aux,
                            "",
                            "")

                        gasList.add(gasAux)
                    }

                    callback(gasList) // Chama o callback quando a leitura é concluída
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "Error getting documents: ", exception)
                    Toast.makeText(context, "Não funcionou", Toast.LENGTH_SHORT).show()
                    //callback(gasList()) // Chama o callback com uma lista vazia em caso de falha
                }
        }
    }


}
