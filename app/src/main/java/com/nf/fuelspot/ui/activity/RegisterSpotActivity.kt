package com.nf.fuelspot.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nf.fuelspot.R
import com.nf.fuelspot.controller.PostoController
import com.nf.fuelspot.databinding.ActivityRegisterSpotBinding
import com.nf.fuelspot.service.GasStationService

class RegisterSpotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterSpotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSpotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("nome")

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val textTittle = findViewById<TextView>(R.id.appTittle)
        val confirmButton = findViewById<TextView>(R.id.login_loginButton)

        binding.loginLoginButton.setOnClickListener {
            val posto = createGasStation()
            GasStationService.gasAddDatabase(posto, it, userName)
        }

        HeaderActivity.createListener(registerButton, loginButton, textTittle, this)
    }

    fun createGasStation(): PostoController {
        val nome = binding.registerNome.text.toString()
        val cnpj = binding.registerCnpj.text.toString()
        val cep = binding.registerCep.text.toString()
        val bairro = binding.registerBairro.text.toString()
        val cidade = binding.registerCidade.text.toString()
        val rua = binding.registerRua.text.toString()
        val numero = binding.registerNumero.text.toString()

        val posto = PostoController()
        posto.createGasStation(nome, cnpj, cep, bairro, cidade, rua, numero)
        return posto
    }
}


//        val registerCep = findViewById<TextView>(R.id.register_cepText)
//        val cep = binding.registerCep.text

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
//
//        Log.d(toString(), "!!!!!!!!!!${cep.toString()}")
//
//
//        // Log.d(toString(),"!!!!!!!!!!${cep}")
//
//
//        confirmButton.setOnClickListener {
//
//
//            val geocoder = Geocoder(this, Locale.getDefault())
//            val addresses = geocoder.getFromLocationName(cep.toString(), 1)
//            val address = addresses!![0]
//
//            binding.registerBairro.setText(address.subLocality.toString())
//            binding.registerCidade.setText(address.subAdminArea.toString())
//            binding.registerRua.setText(address.thoroughfare.toString())


//           val intent = Intent(this, ConfirmGasStationInfoActivity::class.java)
//           this.startActivity(intent)


//            val snackbar = Snackbar.make(
//                it, "info:${address} ", Snackbar.LENGTH_SHORT
//            )
//            snackbar.setBackgroundTint(Color.RED)
//            snackbar.show()
//        }
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

//}