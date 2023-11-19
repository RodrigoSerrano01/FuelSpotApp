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



        val registerButton = findViewById<Button>(R.id.registerButton)
        val logOutButton = findViewById<Button>(R.id.bt_signOut)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)





        /**
         *
         *  Teste de consulta no banco de posto
         *
         **/





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
//         */
//        binding.btSignOut.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val voltarLogin = Intent(this, LoginActivity::class.java)
//            startActivity(voltarLogin)
//            finish()
//        }


        /**
         *
         *  teste função de bottons
         *
         **/




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



