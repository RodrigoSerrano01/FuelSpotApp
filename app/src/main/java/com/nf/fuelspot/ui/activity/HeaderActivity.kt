package com.nf.fuelspot.ui.activity

import android.content.Context
import android.widget.Button
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nf.fuelspot.utils.ButtonActionsUtil

  class  HeaderActivity: AppCompatActivity() {



      companion object {


          fun createListener(
              logOutButton: Button,
              profileButton: Button,
              registerButton: Button,
              loginButton: Button,
              textTittle: TextView,
              context: Context,

              ) {

              logOutButton.setOnClickListener {

                  ButtonActionsUtil.handleLogOutButtonClick(context)


              }

              profileButton.setOnClickListener {
                  ButtonActionsUtil.handleProfileButtonClick(context)
              }

              registerButton.setOnClickListener {
                  ButtonActionsUtil.handleRegisterButtonClick(context)
              }

              loginButton.setOnClickListener {
                  ButtonActionsUtil.handleLoginButtonClick(context)
              }
              textTittle.setOnClickListener {
                  ButtonActionsUtil.handleComeBackTextClick(context)
              }
          }
      }
  }