package com.nf.fuelspot.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nf.fuelspot.databinding.ActivityConfirmGasstationInfoBinding
import com.nf.fuelspot.databinding.ActivityRegisterSpotBinding

class ConfirmGasStationInfoActivity : AppCompatActivity(){

    private lateinit var binding: ActivityConfirmGasstationInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmGasstationInfoBinding.inflate(layoutInflater)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(binding.root)

    }
}

