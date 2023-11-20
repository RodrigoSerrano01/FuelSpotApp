package com.nf.fuelspot.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.nf.fuelspot.controller.GasStationController
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.math.BigDecimal

class APIDistanceUtil {

    data class DistanceMatrixResponse(
        val rows: List<Row>,
    )

    data class Row(
        val elements: List<Element>
    )

    data class Element(
        val distance: Distance,
        val duration: Duration,
    )

    data class Distance(
        val text: String,
    )

    data class Duration(
        val text: String,
    )

    companion object{
        private  var apiKey = "AIzaSyCGh3K1INhVpN8PdkPbr8tadiX_2dhdv8Y"
    fun createData(
        context: Context,
        latAtual: BigDecimal,
        LongAtual: BigDecimal,
        gas: GasStationController
    ){


    val url: okhttp3.HttpUrl = okhttp3.HttpUrl.Builder()
        .scheme("https")
        .host("maps.googleapis.com")
        .addPathSegments("maps/api/distancematrix/json")
        .addQueryParameter("origins", "${LongAtual},${latAtual}")
        .addQueryParameter("destinations", "${gas.getGasLat()},${gas.getGasLong()}")
        .addQueryParameter("key", "${apiKey}")
        .build()
        Log.i("LOG", "!!!!! = ${url}")
    val request = Request.Builder()
        .url(url).get()  // Converte Uri para String
        .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Erro ao fazer a solicitação: ${e.message}")
        }

//        override fun onResponse(call: Call, response: Response) {
//            if (response.isSuccessful) {
//                val responseData = response.body?.string()
//               // println("Resposta JSON: $responseData")
//                //Log.i("LOG", "!!!!! = ${responseData}")
//                // Aqui você pode armazenar a resposta em uma variável, se necessário
//                val resultadoDaSolicitacao: String? = responseData
//                Log.i("LOG", "!!!!! = ${resultadoDaSolicitacao}")
//            } else {
//                println("Erro na solicitação: ${response.code}")
//            }
//        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                val gson = Gson()
                val distanceMatrixResponse = gson.fromJson(responseData, DistanceMatrixResponse::class.java)

                if (distanceMatrixResponse.rows.isNotEmpty()) {
                    val elements = distanceMatrixResponse.rows[0].elements
                    if (elements.isNotEmpty()) {
                        val distanceText = elements[0].distance.text
                        val durationText = elements[0].duration.text

                        gas.setGasDistance(distanceText)
                        gas.setGasDistanceTime(durationText)
                        // Faça o que quiser com as variáveis
                       // Log.i("LOG", "!!Distance Text: ${gas.getGasDistance()}")
                       // Log.i("LOG", "!!Duration Text: $durationText")
                    }
                }

            } else {
                Log.e("LOG", "Erro na solicitação: ${response.code}")
            }
        }
    })

    }
    }
}