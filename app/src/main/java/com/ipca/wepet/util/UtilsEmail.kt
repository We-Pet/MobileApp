package com.ipca.wepet.util

import android.util.Log
import android.util.Patterns

object EmailUtils {
    fun isEmailValid(email: String): Boolean {
        Log.d("EmailValidation", "Email to validate: $email")
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        Log.d("EmailValidation", "Is valid email? $isValid")
        return isValid
    }

}
