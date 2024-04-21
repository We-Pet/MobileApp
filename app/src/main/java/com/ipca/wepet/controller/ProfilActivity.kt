package com.ipca.wepet.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R

class ProfilActivity : AppCompatActivity() {

    private lateinit var ivMainPhoto: ImageView
    private lateinit var tvMainName: TextView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSave: Button
    private lateinit var btnBack : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_layout)

        initializeElements()
        startNewActivities()
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
    }

    private fun startNewActivities() {

        // Login action
        btnSave.setOnClickListener {
            //Call databse
            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}