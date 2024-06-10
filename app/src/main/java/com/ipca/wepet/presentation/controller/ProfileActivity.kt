package com.ipca.wepet.presentation.controller

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.ipca.wepet.R
import com.ipca.wepet.presentation.fragment.user.UserViewModel

class ProfileActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    private lateinit var ivMainPhoto: ImageView
    private lateinit var tvMainName: TextView

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPhone: EditText

    private lateinit var btnSave: Button
    private lateinit var btnBack: ImageButton

    private lateinit var ibName: ImageButton
    private lateinit var ibEmail: ImageButton
    private lateinit var ibAddress: ImageButton
    private lateinit var ibPassword: ImageButton
    private lateinit var ibPhone: ImageButton

    private lateinit var ibShowOrHidePassword: ImageButton

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_layout)

        initializeElements()
        startNewActivities()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST
            )
        }
        fillWithSharedPreferences()
        checkNameAvailability()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeElements() {
        ivMainPhoto = findViewById(R.id.IV_main_photo)
        tvMainName = findViewById(R.id.TV_main_name)
        etName = findViewById(R.id.ET_name)
        etEmail = findViewById(R.id.ET_email)
        etAddress = findViewById(R.id.ET_address)
        etPassword = findViewById(R.id.ET_password)
        etPhone = findViewById(R.id.ET_phone)
        btnSave = findViewById(R.id.BTN_save)
        btnBack = findViewById(R.id.IB_back)
        ibName = findViewById(R.id.IBTN_clear_button_name)
        ibEmail = findViewById(R.id.IBTN_clear_button_email)
        ibAddress = findViewById(R.id.IBTN_clear_button_address)
        ibPassword = findViewById(R.id.IBTN_clear_button_password)
        ibPhone = findViewById(R.id.IBTN_clear_button_phone)
        ibShowOrHidePassword = findViewById(R.id.IBTN_show_or_hide_password)
    }

    private fun checkNameAvailability() {
        if (etName.text.toString().isNotEmpty()) {
            tvMainName.text = etName.text.toString()
        }
    }

    private fun startNewActivities() {
        // Login action
        btnSave.setOnClickListener {
            //Call database
            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
            storeInSharedPreferences()
        }

        ibName.setOnClickListener{ etName.text.clear() }
        ibPhone.setOnClickListener{ etPhone.text.clear() }
        ibAddress.setOnClickListener{ etAddress.text.clear() }
        ibPassword.setOnClickListener{ etPassword.text.clear() }

        // button to show password
        ibShowOrHidePassword.setOnClickListener {
            if (etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ibShowOrHidePassword.setImageResource(R.drawable.hide_password)
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ibShowOrHidePassword.setImageResource(R.drawable.show_password)
            }
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ivMainPhoto.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(cameraIntent)
        }
    }

    private fun storeInSharedPreferences() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE )
        val editor = sharedPreferences.edit()
        val oldPassword: String = sharedPreferences.getString("PASSWORD", "").toString()
        val textFieldMap: MutableMap<String, EditText> = mutableMapOf(
            "NAME" to etName,
            "ADDRESS" to etAddress,
            "PASSWORD" to etPassword,
            "PHONE" to etPhone,
        )

        for (( key, editText ) in textFieldMap){
            editor.putString(key, editText.text.toString())
        }
        editor.apply()
        val newPassword: String = sharedPreferences.getString("PASSWORD", "").toString()
        val email: String = sharedPreferences.getString("EMAIL", "").toString()

        updatePasswordFirebase(email, newPassword, oldPassword)
    }

    private fun fillWithSharedPreferences(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE )

        etName.setText(sharedPreferences.getString("NAME", ""))
        etEmail.setText(sharedPreferences.getString("EMAIL", "" ))
        etAddress.setText(sharedPreferences.getString("ADDRESS", ""))
        etPassword.setText(sharedPreferences.getString("PASSWORD", ""))
        etPhone.setText(sharedPreferences.getString("PHONE", ""))
    }

    // After saving data, update password into firebase
    private fun updatePasswordFirebase(email: String, password: String, oldPassword: String){
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val credential = EmailAuthProvider.getCredential(email, oldPassword)
            user.reauthenticate(credential).addOnCompleteListener {
                authTask ->
                if (authTask.isSuccessful) {
                    user.updatePassword(password)
                        .addOnCompleteListener {
                            updateTask ->
                                if (updateTask.isSuccessful) {
                                    Log.d("AUTH", "Password updated successfully")
                                } else {
                                    Log.e("AUTH", "Password update failed", updateTask.exception)
                                }
                        }
                } else {
                    Log.e("AUTH", "Re-authentication failed", authTask.exception)
                }
            }
        }
    }

    private var cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val photo = result.data?.extras?.get("data") as Bitmap?
                ivMainPhoto.setImageBitmap(photo)
            } else if (result.resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.camera_cancel, Toast.LENGTH_SHORT).show()
            }
        }
}
