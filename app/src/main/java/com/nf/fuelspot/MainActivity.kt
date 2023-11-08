package com.nf.fuelspot
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapView
import com.nf.fuelspot.databinding.ActivityMainBinding
import utils.ButtonActionsUtil

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initReclyclerView()


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

    private fun initReclyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = Adapter_spot(getList())

    }

    private fun getList() = listOf(
        "Matheus",
        "Juan",
        "Gustavo",
        "Rodrigo",
        "Vini"
    )

}
