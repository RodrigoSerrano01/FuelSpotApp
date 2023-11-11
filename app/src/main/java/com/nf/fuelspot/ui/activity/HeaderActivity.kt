package com.nf.fuelspot.ui.activity

import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import utils.ButtonActionsUtil

  class  HeaderActivity: AppCompatActivity() {



      companion object {

          fun createListener(
              registerButton: Button,
              loginButton: Button,
            textTittle: TextView,
              context: Context,

              ) {

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