package com.ipca.wepet.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ipca.wepet.R
import com.ipca.wepet.utils.EmailUtils
import com.ipca.wepet.utils.FirebaseUtils
import com.ipca.wepet.utils.ToastHandler

class SetUpPinActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button

    private lateinit var btnClearTextEmail: ImageButton
    private lateinit var btnClearTextPassword: ImageButton
    private lateinit var btnClearTextPin: ImageButton


    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPin: EditText
    private lateinit var badLoginWarning: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var fireBaseUtils: FirebaseUtils
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_up_pin_layout)

        sharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE)

        initializeElements()
        startNewActivities()
    }

    private fun initializeElements() {
        btnLogin = findViewById(R.id.BTN_login)

        btnClearTextEmail = findViewById(R.id.IBTN_clear_button_email)
        btnClearTextPassword = findViewById(R.id.IBTN_clear_button_password)
        btnClearTextPin = findViewById(R.id.IBTN_clear_button_pin)
        etEmail = findViewById(R.id.ET_email)
        etPassword = findViewById(R.id.ET_password)
        etPin = findViewById(R.id.ET_pin)
        badLoginWarning = findViewById(R.id.bad_login_warning_TV)

        btnClearTextEmail.setOnClickListener { etEmail.text.clear() }
        btnClearTextPassword.setOnClickListener { etPassword.text.clear() }
        btnClearTextPin.setOnClickListener { etPin.text.clear() }

        auth = Firebase.auth
        fireBaseUtils = FirebaseUtils(auth)

        btnBack = findViewById(R.id.IB_back)
    }

    private fun startNewActivities() {
        // Login action
        btnLogin.setOnClickListener {
            checkLoginFieldsAndValidate()
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun checkLoginFieldsAndValidate() {

        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val pin = etPin.text.toString()

        if (email.isBlank()) {
            ToastHandler.showToast(this, R.string.error_empty_email)
            return
        } else if (!EmailUtils.isEmailValid(email)) {
            ToastHandler.showToast(this, R.string.error_empty_email)
        } else if (password.isBlank()) {
            ToastHandler.showToast(this, R.string.error_empty_password)
        } else if (pin.isBlank()) {
            ToastHandler.showToast(this, R.string.error_empty_PIN)
        } else if (pin.length != 6) {
            ToastHandler.showToast(this, R.string.error_size_PIN)
        } else {
            loginFireBase(email, password, pin)
        }
    }

    private fun loginFireBase(email: String, password: String, pin: String) {

        //sign in with firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTH", "signInWithEmail:success")
                    //val user = auth.currentUser
                    Toast.makeText(
                        baseContext,
                        "Authentication successful.",
                        Toast.LENGTH_SHORT,
                    ).show()

                    // Save shared preferences
                    clearOldPin()
                    savePinAndLogin(email, password, pin)

                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AUTH", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun savePinAndLogin(email: String, password: String, pin: String) {
        val editor = sharedPreferences.edit()
        editor.putString("EMAIL", email)
        editor.putString("PASSWORD", password)
        editor.putString("PIN", pin)
        editor.apply()
    }

    private fun clearOldPin() {
        val editor = sharedPreferences.edit()
        editor.remove("PIN")
        editor.apply()
    }
}
