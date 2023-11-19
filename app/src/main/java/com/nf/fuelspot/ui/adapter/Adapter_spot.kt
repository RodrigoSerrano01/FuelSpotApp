package com.nf.fuelspot.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.GasStationController


class Adapter_spot(

    private val context: Context,
    private val listaPosto: List<GasStationController>

) : RecyclerView.Adapter<Adapter_spot.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_spot, parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount() = listaPosto.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val posto = listaPosto[position]
        holder.vincula(posto)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun vincula(gasController: GasStationController) {
            val spotName: TextView = itemView.findViewById(R.id.spotName)
            spotName.text = gasController.getGasName()
            val spotPrice: TextView = itemView.findViewById(R.id.spotPrice)
            spotPrice.text = gasController.getGasPrice()
            val spotReviewScore: TextView = itemView.findViewById(R.id.spotReviewScore)
            spotReviewScore.text = gasController.getGasScore()
            val spotAddress: TextView = itemView.findViewById(R.id.spotStreetName)
            spotAddress.text = gasController.getGasAddress()
            val spotDistance: TextView = itemView.findViewById(R.id.spotDistance)
            spotDistance.text = gasController.getGasDistance()
            val spotDistanceTime: TextView = itemView.findViewById(R.id.spotDistanceTime)
            spotDistanceTime.text = gasController.getGasDistanceTime()


            val teste = itemView.findViewById<ImageView>(R.id.navigationIcon)


            teste.setOnClickListener {
                val gmmIntentUri = Uri.parse("google.navigation:q=${gasController.getGasLat()},${gasController.getGasLong()}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                itemView.context.startActivity(mapIntent)
        }


    }

}
}