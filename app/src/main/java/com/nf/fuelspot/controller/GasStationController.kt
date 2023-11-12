package com.nf.fuelspot.controller

import com.nf.fuelspot.model.Posto
import java.math.BigDecimal


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

    fun createGasStation(name:String,price:BigDecimal,score:BigDecimal,address:String,distance:BigDecimal,distanceTime:BigDecimal){
        this.name = name
        this.price = price
        this.score = score
        this.address = address
        this.distance = distance
        this.distanceTime= distanceTime
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
    fun updateGasDistance(distance:BigDecimal){
        this.distance = distance
    }
    fun updateGasDistanceTime(distanceTime:BigDecimal){
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
        return this.distance.toPlainString()
    }
    fun getGasDistanceTime():String{
        return this.distanceTime.toPlainString()
    }


}