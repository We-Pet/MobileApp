package com.ipca.wepet.presentation.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ipca.wepet.R
import com.ipca.wepet.util.EmailUtils
import com.ipca.wepet.util.ToastHandler

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var btnSubmit: Button

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    private lateinit var clearNameButton: ImageButton
    private lateinit var clearEmailButton: ImageButton
    private lateinit var clearPasswordButton: ImageButton
    private lateinit var clearConfirmPasswordButton: ImageButton
    private lateinit var backToLoginButton: ImageButton

    private lateinit var sharedPreferences: SharedPreferences

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

    private fun initializeElements() {
        nameEditText = findViewById(R.id.ET_name_create_account)
        emailEditText = findViewById(R.id.ET_email_create_account)
        passwordEditText = findViewById(R.id.ET_password_create_account)
        confirmPasswordEditText = findViewById(R.id.ET_confirm_password_create_account)
        btnSubmit = findViewById(R.id.BTN_submit_create_account)

        clearNameButton = findViewById(R.id.IBTN_clear_button_name)
        clearEmailButton = findViewById(R.id.IBTN_clear_button_email)
        clearPasswordButton = findViewById(R.id.IBTN_clear_button_password_create_account)
        clearConfirmPasswordButton =
            findViewById(R.id.IBTN_clear_button_confirm_password_create_account)

        backToLoginButton = findViewById(R.id.BTN_back_to_login_create_account)

        sharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
    }

    private fun setupOnClickListeners() {
        clearNameButton.setOnClickListener { clearText(nameEditText) }
        clearEmailButton.setOnClickListener { clearText(emailEditText) }
        clearPasswordButton.setOnClickListener { clearText(passwordEditText) }
        clearConfirmPasswordButton.setOnClickListener { clearText(confirmPasswordEditText) }
    }

    private fun startNewActivities() {
        // Go back to Login
        backToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setupOnClickListeners()

        btnSubmit.setOnClickListener {

            val password = passwordEditText.text.toString()
            val repeatPassword = confirmPasswordEditText.text.toString()
            val email = emailEditText.text.toString()

            if (email.isBlank()) {
                ToastHandler.showToast(this, R.string.error_empty_email)
                return@setOnClickListener
            } else if (!EmailUtils.isEmailValid(email)) {
                ToastHandler.showToast(this, R.string.error_invalid_email)
                return@setOnClickListener
            } else if (!password.equals(repeatPassword)) {
                ToastHandler.showToast(this, R.string.passwords_do_not_match)
                return@setOnClickListener
            }
            signInUserToFirebase(email, password)
            ToastHandler.showToast(this, R.string.account_created_successfully)
            goToHomePage(email, password)
        }
    }

    private fun goToHomePage(email: String, password: String) {
        val intent = Intent(this, HomePageActivity::class.java)
        setSharedPreferences(email, password)
        startActivity(intent)
    }

    private fun setSharedPreferences(email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("EMAIL", email)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    private fun clearText(editText: EditText) {
        editText.text.clear()
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
