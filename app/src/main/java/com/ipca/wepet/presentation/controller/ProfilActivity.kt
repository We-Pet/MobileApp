package com.ipca.wepet.presentation.controller

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ipca.wepet.R

class ProfilActivity : AppCompatActivity() {
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
    }

    private fun startNewActivities() {
        // Login action
        btnSave.setOnClickListener {
            //Call database
            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
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
