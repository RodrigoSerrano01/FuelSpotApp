package com.nf.fuelspot.ui.activity

import android.content.Intent
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
import com.google.firebase.auth.FirebaseAuth
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.GasStationController
import com.nf.fuelspot.controller.MapController
import com.nf.fuelspot.controller.UserController
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.utils.RecyclerViewGasStationUtil
import java.math.BigDecimal


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)







        /**
         *
         *  Teste de criação de usuario
         *
         **/
        val user1: UserController = UserController()
//        user1.createUser("teste123", "123@gmail.com", "12345678")
        //UserService.userAddDataBase(user1)


//        /**
//         *
//         *  Teste de criação de posto
//         *
//         **/
//        val gas1: GasStationController = GasStationController()
//        gas1.createGasStation(
//            "teste", BigDecimal("10.00"), BigDecimal("10.00"),
//            "Rua 1", BigDecimal("10.00"), BigDecimal("10.00")
//        )
//
//        val gas2: GasStationController = GasStationController()
//        gas2.createGasStation(
//            this,"teste2", BigDecimal("10.00"), BigDecimal("10.00"),
//            "Rua 1", BigDecimal("10.00"), BigDecimal("10.00"))
////
//        val gasList: MutableList<GasStationController> = mutableListOf<GasStationController>()
////
////        gasList.add(gas1)
//        gasList.add(gas2)
//
//        RecyclerViewGasStationUtil.addGasStationToRicylerView(recyclerView, this, gasList)


        /**
         *
         *  Teste de criação de Mapa
         *
         **/
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val endereco = "Herval do Oeste, São tomé, viamão RS"




        /**
         * Como só temos a tela principal, com login e cadastre-se aparecendo mesmo com o usuário
         * já estando cadastrado, ele está sendo redirecionado por um método na activity de login
         * TODO arrumar essa lógica após termos as duas telas mapa
         */
        binding.btSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarLogin = Intent(this, LoginActivity::class.java)
            startActivity(voltarLogin)
            finish()
        }


        /**
         *
         *  teste função de bottons
         *
         **/




        HeaderActivity.createListener(registerButton, loginButton, textTittle, this)


    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val gas1: GasStationController = GasStationController()
        gas1.createGasStation(
            this,"teste", BigDecimal("10.00"), BigDecimal("10.00"),
            "Herval do Oeste, São tomé, viamão RS, 316", BigDecimal("10.00"), BigDecimal("10.00"))

        val gas2: GasStationController = GasStationController()
        gas2.createGasStation(
            this,"casa juan", BigDecimal("10.00"), BigDecimal("10.00"),
            "91370-170", BigDecimal("10.00"), BigDecimal("10.00"))

        val gasList: MutableList<GasStationController> = mutableListOf<GasStationController>()
        gasList.add(gas1)
        gasList.add(gas2)

        RecyclerViewGasStationUtil.addGasStationToRicylerView(recyclerView, this, gasList)

        Log.d(toString(),gas1.getStringCoordinate())

        var testeMap: MapController = MapController()
        val gasLocation = LatLng(gas1.getGasLat(),gas1.getGasLong())

        val gasLocation1 = LatLng(gas2.getGasLat(),gas2.getGasLong())



        if (testeMap.permissionTest(this, this)) {
            testeMap.addGasStationMarker(googleMap, gasLocation, gas1)
            testeMap.addGasStationMarker(googleMap, gasLocation1, gas2)

        } else {
            recreate();
        }


//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                location?.let {
//                    val userLocation = LatLng(location.latitude, location.longitude)
//                    //val userLocation = LatLng(-30.12067486761821, -51.07503957487643)
//                    mMap.clear()
//                    mMap.addMarker(
//                        MarkerOptions().position(userLocation).title("Sua Localização Atual")
//                    )
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
//                }
//            }
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                1
//            )
//        }
    }


}



