package com.ipca.wepet.utils

import android.util.Patterns

object EmailUtils {
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
