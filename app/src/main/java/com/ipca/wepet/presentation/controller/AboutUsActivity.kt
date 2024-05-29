package com.ipca.wepet.presentation.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R

class AboutUsActivity : AppCompatActivity() {

    private lateinit var tvNumberUsers: TextView
    private lateinit var tvNumberAnimals: TextView

    private lateinit var btnContact: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_us_layout)

        initializeElements()
        startNewActivities()
    }

    private fun initializeElements() {
        tvNumberUsers = findViewById(R.id.TV_number_of_users)
        tvNumberAnimals = findViewById(R.id.TV_number_of_animals)
        btnContact = findViewById(R.id.BTN_contact)
        btnBack = findViewById(R.id.BTN_back)
    }

    private fun startNewActivities() {

        // Contact action
        btnContact.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}