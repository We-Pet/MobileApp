package com.ipca.wepet.interfaces

interface IFirebaseService {
    fun checkIfUserIsLoggedIn(callBack: () -> Unit)
    fun sendEmailResetPassword(emailAddress: String, callBack: () -> Unit)
    fun checkIfUserExists(email: String): Boolean
}