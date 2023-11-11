package com.nf.fuelspot.controller

import android.util.Log
import com.nf.fuelspot.model.Owner
import com.nf.fuelspot.model.Posto



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

class OwnerController : Owner(){

    fun addGasStation(newGasStation : Posto){

        gasStationList.add(newGasStation)
    }

    fun printAll (){
        gasStationList.forEach(){
            Log.d(toString(),it.toString())
        }
    }

    fun deleteByIndex (index: Int){
        gasStationList.removeAt(index)
    }
    fun searchByIndex(index: Int): Posto{
        return gasStationList.get(index)
    }
}
