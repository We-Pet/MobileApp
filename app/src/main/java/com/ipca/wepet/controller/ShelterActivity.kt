package com.ipca.wepet.controller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.models.ShelterModel

class ShelterActivity : AppCompatActivity() {
    private lateinit var tvShelterId: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shelter_profil_layout)

        initializeElements()

        val shelterId = intent.getStringExtra("shelter")
        shelterId?.let { getShelterInfo(shelterId) }
    }

    private fun initializeElements() {
        tvShelterId = findViewById(R.id.TV_shelter_id)
    }

    private fun getShelterInfo(shelterId: String) {
        // Get animal from database

        val shelter = ShelterModel(shelterId, "Tom", "Cat", "Male")
        showShelterDetails(shelter)
    }

    private fun showShelterDetails(shelter: ShelterModel) {
        tvShelterId.text = "Id: ${shelter.id}"
    }
}