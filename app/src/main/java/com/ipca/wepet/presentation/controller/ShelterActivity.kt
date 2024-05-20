package com.ipca.wepet.presentation.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.ipca.wepet.R
import com.ipca.wepet.presentation.fragment.FooterFragment

class ShelterActivity : AppCompatActivity() {
    private lateinit var ivShelterPhoto: ImageView
    private lateinit var tvShelterName: TextView
    private lateinit var tvShelterLocation: TextView
    private lateinit var tvShelterDescription: TextView


    private lateinit var ibBack: ImageButton
    private lateinit var ibLike: ImageButton
    private lateinit var contactCard: CardView
    private lateinit var animalCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shelter_profil_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_footer, FooterFragment())
            .commit()

        initializeElements()
        startNewActivities()

        val shelterId = intent.getStringExtra("shelter")
    }

    private fun initializeElements() {
        ibBack = findViewById(R.id.IB_back)
        ibLike = findViewById(R.id.IB_like)
        ivShelterPhoto = findViewById(R.id.IV_shelterPhoto)
        tvShelterName = findViewById(R.id.TV_shelterName)
        tvShelterLocation = findViewById(R.id.TV_shelterLocation)
        tvShelterDescription = findViewById(R.id.TV_shelterDescription)
        contactCard = findViewById(R.id.contact_card)
        animalCard = findViewById(R.id.animal_card)
    }


    private fun startNewActivities() {

        contactCard.setOnClickListener {
            //Call contact
            Toast.makeText(this, "Contacted successfully!", Toast.LENGTH_SHORT).show()
        }

        animalCard.setOnClickListener {
            //Call animal list with filters
            Toast.makeText(this, "Animal successfully!", Toast.LENGTH_SHORT).show()
        }

        ibLike.setOnClickListener {
            //Call action for like
            Toast.makeText(this, "Liked successfully!", Toast.LENGTH_SHORT).show()
        }

        // Back action
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvShelterLocation.setOnClickListener {
            val intent = Intent(this, GoogleMapsActivity::class.java)
            //TODO: get lat and long from database
            intent.putExtra("latitude", 41.5369644)
            intent.putExtra("longitude", -8.6286399)
            intent.putExtra("name", tvShelterLocation.text)
            startActivity(intent)
        }
    }
}