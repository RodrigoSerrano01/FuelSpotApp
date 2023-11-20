package com.nf.fuelspot.controller

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapController {

    private lateinit var mMap: GoogleMap



    fun permissionTest(context: Context, activity: Activity):Boolean {

        if(ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        else{
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
            return false
        }
    }


    fun addGasStationMarker (googleMap: GoogleMap, location: LatLng, gasStationController: GasStationController){

        val mMap= googleMap

        mMap.addMarker(
            MarkerOptions().position(location).title(gasStationController.getGasName())
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }


}