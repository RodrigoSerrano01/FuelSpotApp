package com.nf.fuelspot.ui.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nf.fuelspot.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_profile)

        val mySpotsButton = findViewById<Button>(R.id.checkSpotsButton)

    mySpotsButton.setOnClickListener {
        val intent = Intent(this, SpotListActivity::class.java)
        startActivity(intent)
    }

    }


}