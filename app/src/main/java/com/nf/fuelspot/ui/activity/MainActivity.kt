package com.nf.fuelspot.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.nf.fuelspot.databinding.ActivityMainBinding
import com.nf.fuelspot.controller.UserController

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

        //val listaUser = mutableListOf<UserService>()


        val userS : UserController = UserController()

        userS.createUser("Rodrigo","Rodrigo@gmail.com")

        val user1 : UserController = UserController()

        user1.createUser("Rodrigo1","Rodrigo1@gmail.com")
        val user2 : UserController = UserController()

        user2.createUser("Rodrigo2","Rodrigo2@gmail.com")

        val listaUser = mutableListOf<UserController>(userS,user1)

        listaUser.add(user2)

        listaUser.forEach{
            Log.d(toString(),it.toString())
        }



        //user.createUser("Rodrigo","Rodrigo@gmail.com")

        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

                val registerButton = findViewById<Button>(R.id.registerButton)
                val loginButton = findViewById<Button>(R.id.loginButton)
                val textTittle = findViewById<TextView>(R.id.appTittle)

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
                HeaderActivity.createListener(registerButton,loginButton,textTittle,this)

                val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)



        //        val registerButton = findViewById<Button>(R.id.registerButton)
        //        val loginButton = findViewById<Button>(R.id.loginButton)
        //       // val textTittle = findViewById<TextView>(R.id.appTittle)
        //
        //        registerButton.setOnClickListener {
        //            ButtonActionsUtil.handleRegisterButtonClick(this)
        //        }
        //
        //        loginButton.setOnClickListener {
        //            ButtonActionsUtil.handleLoginButtonClick(this)
        //        }
        ////        textTittle.setOnClickListener {
        ////            ButtonActionsUtil.handleComeBackTextClick(this)
        ////        }

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
                            //val userLocation = LatLng(-30.12067486761821, -51.07503957487643)
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
        */
    }

     override fun onMapReady(p0: GoogleMap) {
         TODO("Not yet implemented")
     }
 }
