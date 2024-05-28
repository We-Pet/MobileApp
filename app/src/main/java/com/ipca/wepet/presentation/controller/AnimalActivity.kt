package com.ipca.wepet.presentation.controller

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.presentation.fragment.FooterFragment
import java.io.Serializable

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

        val animal = intent.getSerializableExtra("animal") as? AnimalModel
        if (animal != null) {
            showAnimalDetails(animal)
        }

    }

    inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
            key,
            T::class.java
        )

        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
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

    private fun showAnimalDetails(animal: AnimalModel) {
        tvAnimalName.text = "${animal.name}"
        tvAnimalAge.text = "${animal.name}"
        tvAnimalStatus.text = "${animal.name}"
        tvAnimalGender.text = "${animal.gender}"
        tvAnimalLocation.text = "${animal.city}"
        tvAnimalDescription.text = "${animal.description}"
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

        tvAnimalLocation.setOnClickListener {
            val intent = Intent(this, GoogleMapsActivity::class.java)
            //TODO: get lat and long from database
            intent.putExtra("latitude", 41.5369644)
            intent.putExtra("longitude", -8.6286399)
            intent.putExtra("name", tvAnimalLocation.text)
            startActivity(intent)
        }
    }
}