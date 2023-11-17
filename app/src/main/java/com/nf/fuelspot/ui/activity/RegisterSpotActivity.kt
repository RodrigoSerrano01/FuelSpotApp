package com.nf.fuelspot.ui.activity

import android.content.pm.ActivityInfo
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nf.fuelspot.R
import com.nf.fuelspot.databinding.ActivityRegisterSpotBinding
import java.util.Locale

class RegisterSpotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterSpotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSpotBinding.inflate(layoutInflater)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(binding.root)


         val logOutButton = findViewById<Button>(R.id.bt_signOut)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)
        val confirmButton = findViewById<TextView>(R.id.login_loginButton)


        HeaderActivity.createListener(logOutButton, profileButton, registerButton, loginButton, textTittle, this)

        val registerCep = findViewById<TextView>(R.id.register_cepText)
        val cep = binding.registerCep.text

//        val gas1: GasStationController = GasStationController()
//        gas1.createGasStation(
//            this,"teste",
//            java.math.BigDecimal("10.00"),
//            java.math.BigDecimal("10.00"),
//            "94460-090",
//            java.math.BigDecimal("10.00"),
//            java.math.BigDecimal("10.00")
//        )


        //Log.d(toString(),"!!!!!!!!!!${onFocusChangeListener}")

        Log.d(toString(), "!!!!!!!!!!${cep.toString()}")


        // Log.d(toString(),"!!!!!!!!!!${cep}")


        confirmButton.setOnClickListener {


            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(cep.toString(), 1)
            val address = addresses!![0]

            binding.registerBairro.setText(address.subLocality.toString())
            binding.registerCidade.setText(address.subAdminArea.toString())
            binding.registerRua.setText(address.thoroughfare.toString())


//           val intent = Intent(this, ConfirmGasStationInfoActivity::class.java)
//           this.startActivity(intent)


//            val snackbar = Snackbar.make(
//                it, "info:${address} ", Snackbar.LENGTH_SHORT
//            )
//            snackbar.setBackgroundTint(Color.RED)
//            snackbar.show()
        }
//        binding.loginLoginButton.setOnClickListener {
//
//            val geocoder = Geocoder(this, Locale.getDefault())
//            val addresses = geocoder.getFromLocationName(registerCep.toString(), 1)
//            val address = addresses!![0]
//
//            val snackbar = Snackbar.make(
//                it, "Todos os campos precisam estar preenchidos!", Snackbar.LENGTH_SHORT
//            )
//            snackbar.setBackgroundTint(Color.RED)
//            snackbar.show()
        //       }
    }

}