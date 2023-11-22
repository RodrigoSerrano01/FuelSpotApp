package com.nf.fuelspot.ui.activity

import android.content.ContentValues.TAG
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.MapController
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.service.GasStationService
import com.nf.fuelspot.utils.APIDistanceUtil
import com.nf.fuelspot.utils.RecyclerViewGasStationUtil
import java.math.BigDecimal
import java.util.Objects
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var LONGITUDE = BigDecimal(-30.12086083470527)
    private var LATITUDE = BigDecimal(-51.07528734639126)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(binding.root)
        val user = FirebaseAuth.getInstance()
        val registerButton = findViewById<Button>(R.id.registerButton)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)


        /**
         * teste header
         */


        val logOutButton = findViewById<Button>(R.id.bt_signOut)
        val profButton = findViewById<Button>(R.id.profileButton)



        if (user.currentUser != null) {
            Log.d(TAG, "!!Logado")
            profButton.setEnabled(true)
            logOutButton.setEnabled(true)
            profButton.setVisibility(View.VISIBLE)
            logOutButton.setVisibility(View.VISIBLE)
            loginButton.setEnabled(false)
            registerButton.setEnabled(false)
            loginButton.setVisibility(View.GONE)
            registerButton.setVisibility(View.GONE)
        }

        logOutButton.setOnClickListener {
            Log.d(TAG, "!!Logado não")
            user.signOut()
            profButton.setEnabled(false)
            logOutButton.setEnabled(false)
            profButton.setVisibility(View.GONE)
            logOutButton.setVisibility(View.GONE)
            loginButton.setEnabled(true)
            registerButton.setEnabled(true)
            loginButton.setVisibility(View.VISIBLE)
            registerButton.setVisibility(View.VISIBLE)
            //recreate();
        }

        //profileButton


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        HeaderActivity.createListener(
            logOutButton,
            profileButton,
            registerButton,
            loginButton,
            textTittle,
            this
        )


        /**
         * tentativa de calculo de distancia
         * */


        // Log.i("LOG", "A Distancia é = ${url.toString()}")
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val long = LONGITUDE
        val lat = LATITUDE


        var testeMap = MapController()

        GasStationService.gasConsult(this) { lista ->
            val totalCalls = lista.size
            var completedCalls = 0
            if (testeMap.permissionTest(this, this)) {
                do {

                lista.forEach {

                    if(APIDistanceUtil.createData(this, lat, long, it)) {
                        completedCalls++
                    }

                       // Log.i("LOG", "!!:${completedCalls}")
                        val gasLocation = LatLng(it.getGasLat(), it.getGasLong())
                        testeMap.addGasStationMarker(googleMap, gasLocation, it)

                            if (completedCalls == totalCalls) {
                                Thread.sleep(2000)
                              //  Log.i("LOG", "!!Entrou:${completedCalls}")
                                RecyclerViewGasStationUtil.addGasStationToRicylerView(
                                    recyclerView,
                                    this,
                                    lista
                                )
                            }


                }
                }while (completedCalls != totalCalls)
            } else {
                recreate();
            }


            }

    }


}


