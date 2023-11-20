package com.nf.fuelspot.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.MapController
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.service.GasStationService
import com.nf.fuelspot.utils.APIDistanceUtil
import com.nf.fuelspot.utils.RecyclerViewGasStationUtil
import java.math.BigDecimal


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(binding.root)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val logOutButton = findViewById<Button>(R.id.bt_signOut)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        HeaderActivity.createListener(logOutButton, profileButton, registerButton, loginButton, textTittle, this)




/**
* tentativa de calculo de distancia
* */





       // Log.i("LOG", "A Distancia é = ${url.toString()}")
    }




    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val long = BigDecimal(-30.0125097)
        val lat = BigDecimal(-51.146238999999994)


        var testeMap = MapController()
        GasStationService.gasConsult(this) { lista ->

            if (testeMap.permissionTest(this, this)) {
                lista.forEach {
                    APIDistanceUtil.createData(this,lat,long,it)

                    val gasLocation = LatLng(it.getGasLat(),it.getGasLong())
                    testeMap.addGasStationMarker(googleMap, gasLocation, it)
                    Log.i("LOG", "!!D: ${it.getGasName()}")
                }
            } else {
                recreate();
            }
            //Log.i("LOG", "!!D: ${lista.get(0).getGasDistance()}")
            RecyclerViewGasStationUtil.addGasStationToRicylerView(recyclerView, this, lista)
        }
    }
}



