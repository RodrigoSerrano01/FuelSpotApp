package utils

import android.content.Context
import android.content.Intent
import com.nf.fuelspot.LoginActivity
import com.nf.fuelspot.RegisterActivity

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
    }
}
