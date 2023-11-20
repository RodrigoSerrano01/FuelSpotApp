package com.nf.fuelspot.controller

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.nf.fuelspot.model.Posto
import java.math.BigDecimal
import java.util.Locale


/**
 *
 *      Author: Rodrigo Serrano
 *
 *      classe de controle de proprietario
 *
 *      extende owner
 *
 *      cria e atualizada os dados de proprietario
 *
 * **/


class GasStationController : Posto() {
     private lateinit var context:Context

    fun createGasStation(
        context: Context,
        name:String,
        price:BigDecimal,
        score:BigDecimal,
        address:String,
        distance: String,
        distanceTime:String){
        this.name = name
        this.price = price
        this.score = score
        this.address = address
        this.distance = distance
        this.distanceTime= distanceTime
        coordinatesByAdrres(context)
    }

    fun updateGasName(name:String){
        this.name = name
    }
    fun updateGasPrice(price:BigDecimal){
        this.price = price
    }

    fun updateGasScore(score:BigDecimal){
        this.score = score
    }
    fun updateGasAddress(address:String){
        this.address = address
    }
    fun updateGasDistance(distance:String){
        this.distance = distance
    }
    fun updateGasDistanceTime(distanceTime:String){
        this.distanceTime= distanceTime
    }


    fun getGasName():String{
        return this.name
    }
    fun getGasPrice():String{
        return this.price.toPlainString()
    }
    fun getGasScore():String{
        return this.score.toPlainString()
    }
    fun getGasAddress():String{
        return this.address
    }
    fun getGasDistance():String{
        return this.distance
    }
    fun getGasDistanceTime():String{
        Log.i("LOG", "!!D: ${this.distanceTime}")
        return this.distanceTime
    }

    fun setGasDistance(distance: String){
         this.distance = distance
    }
    fun setGasDistanceTime(distanceTime: String){
         this.distanceTime =distanceTime
    }
    fun getGasLong():Double{
        return this.longitude.toDouble()
    }
    fun getGasLat():Double{
        return this.latitude.toDouble()
    }

    fun getStringCoordinate():String{
        return "Longitude:${this.longitude} Latitude:${this.latitude}"
    }

    fun coordinatesByAdrres (context: Context){


        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocationName(this.address, 1)
        val address = addresses!![0]
        this.longitude = address.longitude.toBigDecimal()
        this.latitude = address.latitude.toBigDecimal()



    }

    override fun toString(): String {
        return "${name}, ${price},${score}, ${address} "
    }



}