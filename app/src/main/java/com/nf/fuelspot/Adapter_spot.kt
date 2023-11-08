package com.nf.fuelspot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class Adapter_spot(
    private val myList: List<String>

) : RecyclerView.Adapter<Adapter_spot.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_spot, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = myList[position]
        val price = myList[position]
        val score = myList[position]
        val address = myList[position]
        val distance = myList[position]
        val distanceTime = myList[position]

        holder.spotName.text = name
        holder.spotPrice.text = price
        holder.spotReviewScore.text = score
        holder.spotAddress.text = address
        holder.spotDistance.text = distance
        holder.spotDistanceTime.text = distanceTime

    }
    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spotName: TextView = itemView.findViewById(R.id.spotName)
        val spotPrice: TextView = itemView.findViewById(R.id.spotPrice)
        val spotReviewScore: TextView = itemView.findViewById(R.id.spotReviewScore)
        val spotAddress: TextView = itemView.findViewById(R.id.spotStreetName)
        val spotDistance: TextView = itemView.findViewById(R.id.spotDistance)
        val spotDistanceTime: TextView = itemView.findViewById(R.id.spotDistanceTime)
    }
}