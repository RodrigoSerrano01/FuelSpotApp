package com.nf.fuelspot.utils

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.GasStationController
import com.nf.fuelspot.ui.adapter.Adapter_spot

class RecyclerViewGasStationUtil {

    companion object{


        fun addGasStationToRicylerView (recyclerView: RecyclerView, contextAux: Context,list: List<GasStationController>){

            recyclerView.adapter = Adapter_spot(
            context = contextAux, listaPosto = list
        )
        }
    }

}