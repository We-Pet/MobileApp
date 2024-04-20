package com.ipca.wepet.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ipca.wepet.R

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account_layout)

        auth = Firebase.auth

        initializeElements()
        startNewActivities()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = null // auth.currentUser Todo: when logout is implemented

    }

    private fun startNewActivities() {
        //Back action
        val btnLogin = findViewById<View>(R.id.BTN_back)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSubmit.setOnClickListener {
            val name = findViewById<EditText>(R.id.ET_name)
            val email = findViewById<EditText>(R.id.ET_email)
            val password = findViewById<EditText>(R.id.ET_pass)
            val repeatPassword = findViewById<EditText>(R.id.ET_repeat_pass)

            if (password.text.toString() != repeatPassword.text.toString()) {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            signInUserToFirebase(email.text.toString(), password.text.toString())
            //auth.createUserWithEmailAndPassword()
        }
    }

    private fun initializeElements() {
        btnSubmit = findViewById<Button>(R.id.BTN_submit)
    }

    private fun signInUserToFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signIn", "createUserWithEmail:success")
                    val user = auth.currentUser
                    setContentView(R.layout.login_layout)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("signIn", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
    }
}