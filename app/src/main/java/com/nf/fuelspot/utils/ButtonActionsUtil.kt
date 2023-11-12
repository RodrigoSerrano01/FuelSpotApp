package com.nf.fuelspot.utils

import android.content.Context
import android.content.Intent
import com.nf.fuelspot.ui.activity.LoginActivity
import com.nf.fuelspot.ui.activity.MainActivity
import com.nf.fuelspot.ui.activity.RegisterActivity

class ButtonActionsUtil {
    companion object {
        fun handleRegisterButtonClick(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }

        fun handleLoginButtonClick(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }

        fun handleComeBackTextClick(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
