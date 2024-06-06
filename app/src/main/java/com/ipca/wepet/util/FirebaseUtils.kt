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

    fun sendEmailResetPassword(emailAddress: String, callBack: () -> Unit) {
        if(checkIfUserExists(emailAddress)){
            _auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("EMAIL", "Email sent.")
                    }
                }
        }
    }
     fun checkIfUserExists(email: String): Boolean{
        return true //TODO
    }
}