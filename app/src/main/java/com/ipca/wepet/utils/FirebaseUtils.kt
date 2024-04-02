package com.ipca.wepet.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseUtils(auth: FirebaseAuth) {
    private val _auth: FirebaseAuth = auth

    fun checkIfUserIsLoggedIn(callBack: () -> Unit){
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = _auth.currentUser
        if (currentUser != null) {
            callBack()
        }
    }
}