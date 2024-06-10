package com.ipca.wepet.util

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class FirebaseUtils(auth: FirebaseAuth) {
    private val _auth: FirebaseAuth = auth

    fun checkIfUserIsLoggedIn(callBack: () -> Unit) {
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = _auth.currentUser
        if (currentUser != null) {
            callBack()
        }
    }

    fun sendEmailResetPassword(emailAddress: String, callBack: (Boolean) -> Unit) {
        if(checkIfUserExists(emailAddress)){
            _auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callBack(true)
                        Log.d("EMAIL", "Email sent.")
                    } else {
                        callBack(false)
                        Log.d("EMAIL", "Failed to send email: ${task.exception?.message}")
                    }
                }
        }
    }
     fun checkIfUserExists(email: String): Boolean{
        return true //TODO
    }
}