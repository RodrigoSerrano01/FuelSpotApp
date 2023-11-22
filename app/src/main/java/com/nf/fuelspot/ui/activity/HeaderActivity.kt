package com.nf.fuelspot.ui.activity

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Button
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.nf.fuelspot.utils.ButtonActionsUtil

  class  HeaderActivity: AppCompatActivity() {



      companion object {

          private val user = FirebaseAuth.getInstance()
          fun createListener(
              logOutButton: Button,
              profileButton:Button,
              registerButton: Button,
              loginButton: Button,
              textTittle: TextView,
              context: Context,

              ) {

              logOutButton.setOnClickListener {
                  Log.d(ContentValues.TAG, "!!Logado não")
                  user.signOut()
                  profileButton.setEnabled(false)
                  logOutButton.setEnabled(false)
                  profileButton.setVisibility(View.GONE)
                  logOutButton.setVisibility(View.GONE)
                  loginButton.setEnabled(true)
                  registerButton.setEnabled(true)
                  loginButton.setVisibility(View.VISIBLE)
                  registerButton.setVisibility(View.VISIBLE)
                  ButtonActionsUtil.handleComeBackLogoutButton(context)
              }

              registerButton.setOnClickListener {
                  ButtonActionsUtil.handleRegisterButtonClick(context)
              }
              profileButton.setOnClickListener {
                  ButtonActionsUtil.handleComeBackProfile(context)
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