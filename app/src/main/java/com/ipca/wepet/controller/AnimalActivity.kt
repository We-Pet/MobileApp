package com.ipca.wepet.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.ipca.wepet.R
import com.ipca.wepet.fragment.FooterFragment
import com.ipca.wepet.models.AnimalModel
import com.ipca.wepet.models.ShelterModel

class AnimalActivity : AppCompatActivity() {
    private lateinit var ivAnimalPhoto: ImageView
    private lateinit var tvAnimalName: TextView
    private lateinit var tvAnimalRace: TextView
    private lateinit var tvAnimalAge: TextView
    private lateinit var tvAnimalStatus: TextView
    private lateinit var tvAnimalGender: TextView
    private lateinit var tvAnimalLocation: TextView
    private lateinit var tvAnimalDescription: TextView

    private lateinit var ibBack: ImageButton
    private lateinit var ibLike: ImageButton
    private lateinit var visitCard: CardView
    private lateinit var shelterCard: CardView
    private lateinit var ivShelterIcon: ImageView
    private lateinit var tvShelterName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animal_profil_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_footer, FooterFragment())
            .commit()

        initializeElements()
        startNewActivities()

        val animalId = intent.getStringExtra("animal")
        animalId?.let { getAnimalInfo(animalId) }

    }

    private fun initializeElements() {
        ibBack = findViewById(R.id.IB_back)
        ibLike = findViewById(R.id.IB_like)
        visitCard = findViewById(R.id.visit_card)
        shelterCard = findViewById(R.id.shelter_card)
        ivShelterIcon = findViewById(R.id.IV_shelter_icon)
        tvShelterName = findViewById(R.id.TV_shelter_name)

        ivAnimalPhoto = findViewById(R.id.IV_animalPhoto)
        tvAnimalName = findViewById(R.id.TV_animalName)
        tvAnimalRace = findViewById(R.id.TV_animalRace)
        tvAnimalAge = findViewById(R.id.TV_animalAge)
        tvAnimalStatus = findViewById(R.id.TV_animalStatus)
        tvAnimalGender = findViewById(R.id.TV_animalGender)
        tvAnimalLocation = findViewById(R.id.TV_animalLocation)
        tvAnimalDescription = findViewById(R.id.TV_animalDescription)
    }

    private fun getAnimalInfo(animalId: String) {
        // Get animal from database

        val animal = AnimalModel(animalId, "Tom", "Cat", "Male")
        showAnimalDetails(animal)
        getShelterInfo(animal.id)
    }


    private fun getShelterInfo(shelterId: String) {
        // Get shelter from database

        val shelter = ShelterModel(shelterId, "Tom", "Cat", "Male")
        showShelterDetails(shelter)
    }

    private fun showAnimalDetails(animal: AnimalModel) {
        //tvAnimalId.text = "Id: ${animal.id}"
    }

    private fun showShelterDetails(shelter: ShelterModel) {
        //ivShelterIcon.image
        tvShelterName.text = "Shelter: ${shelter.name}"
    }

    private fun startNewActivities() {

        visitCard.setOnClickListener {
            //Call visit
            Toast.makeText(this, "Visit saved successfully!", Toast.LENGTH_SHORT).show()
        }

        shelterCard.setOnClickListener {
            //Call shelter
            Toast.makeText(this, "Shelter saved successfully!", Toast.LENGTH_SHORT).show()
        }

        ibLike.setOnClickListener {
            //Call action for like
            Toast.makeText(this, "Liked successfully!", Toast.LENGTH_SHORT).show()
        }

        // Back action
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvAnimalLocation.setOnClickListener{
            val intent = Intent(this, GoogleMapsActivity::class.java)
            //TODO: get lat and long from database
            intent.putExtra("latitude", 41.5369644)
            intent.putExtra("longitude", -8.6286399,)
            intent.putExtra("name", tvAnimalLocation.text)
            startActivity(intent)
        }
    }
}