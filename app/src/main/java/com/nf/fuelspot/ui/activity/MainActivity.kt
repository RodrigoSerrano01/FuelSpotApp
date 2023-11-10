package com.nf.fuelspot.ui.activity
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nf.fuelspot.ui.adapter.Adapter_spot
import com.nf.fuelspot.R
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.model.Posto
import utils.ButtonActionsUtil
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  initReclyclerView()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = Adapter_spot(context = this, listaPosto = listOf(
            Posto(name = "teste", price = BigDecimal("10.00")
                , score = BigDecimal("10.00"), address = "Rua 1", distance = BigDecimal("10.00"), distanceTime = BigDecimal("10.00")),
            Posto(name = "teste2", price = BigDecimal("20.00")
                , score = BigDecimal("20.00"), address = "Rua 2", distance = BigDecimal("20.00"), distanceTime = BigDecimal("20.00")),
            Posto(name = "teste3", price = BigDecimal("10.00")
                , score = BigDecimal("10.00"), address = "Rua 1", distance = BigDecimal("10.00"), distanceTime = BigDecimal("10.00")),
            Posto(name = "teste4", price = BigDecimal("20.00")
                , score = BigDecimal("20.00"), address = "Rua 2", distance = BigDecimal("20.00"), distanceTime = BigDecimal("20.00"))
        )
        )


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        registerButton.setOnClickListener {
            ButtonActionsUtil.handleRegisterButtonClick(this)
        }

        loginButton.setOnClickListener {
            ButtonActionsUtil.handleLoginButtonClick(this)
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
             fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val userLocation = LatLng(location.latitude, location.longitude)

                    mMap.clear()

                    mMap.addMarker(MarkerOptions().position(userLocation).title("Sua Localização Atual"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

//    private fun initReclyclerView() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.setHasFixedSize(true)
//        binding.recyclerView.adapter = Adapter_spot(getList())
//
//    }
//
//    private fun getList() = listOf(
//        "Matheus",
//        "Juan",
//        "Gustavo",
//        "Rodrigo",
//        "Vini"
//    )

}
