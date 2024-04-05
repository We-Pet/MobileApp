package com.ipca.wepet.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ipca.wepet.interfaces.IFirebaseService

class FirebaseUtils(auth: FirebaseAuth) : IFirebaseService {
    private val _auth: FirebaseAuth = auth

    override fun checkIfUserIsLoggedIn(callBack: () -> Unit){
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = _auth.currentUser
        if (currentUser != null) {
            callBack()
        }
    }

    override fun sendEmailResetPassword(emailAddress: String, callBack: () -> Unit) {
        if(checkIfUserExists(emailAddress)){
            _auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("EMAIL", "Email sent.")
                    }
                }
        }
    }

    override fun checkIfUserExists(email: String): Boolean{
        return true //TODO
    }
}