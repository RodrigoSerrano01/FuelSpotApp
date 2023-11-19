package com.nf.fuelspot.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.MapController
import com.nf.fuelspot.controller.UserController
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.service.GasStationService
import com.nf.fuelspot.utils.RecyclerViewGasStationUtil


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(binding.root)

        val positionToScroll = 5 // Substitua pelo índice da posição desejada


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




    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)



        var testeMap = MapController()
        GasStationService.gasConsult(this) { lista ->
            RecyclerViewGasStationUtil.addGasStationToRicylerView(recyclerView, this, lista)
            if (testeMap.permissionTest(this, this)) {
                lista.forEach {
                    val gasLocation = LatLng(it.getGasLat(),it.getGasLong())
                    testeMap.addGasStationMarker(googleMap, gasLocation, it)
                }
            } else {
                recreate();
            }
        }

    }
}



