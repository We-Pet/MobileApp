package com.ipca.wepet.presentation.controller

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.presentation.fragment.FooterFragment
import com.ipca.wepet.util.ToastHandler
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AnimalActivity : AppCompatActivity() {
    private lateinit var ivAnimalPhoto: ImageView
    private lateinit var tvAnimalName: TextView
    private lateinit var tvAnimalRace: TextView
    private lateinit var tvAnimalAge: TextView
    private lateinit var tvAnimalStatus: TextView
    private lateinit var tvAnimalGender: TextView
    private lateinit var tvAnimalLocation: TextView
    private lateinit var tvAnimalDescription: TextView
    private lateinit var tvAnimalLatitude: TextView
    private lateinit var tvAnimalLongitude: TextView


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
        tvAnimalLatitude = findViewById(R.id.TV_animalLatitude)
        tvAnimalLongitude = findViewById(R.id.TV_animalLongitude)
    }

    private fun showAnimalDetails(animal: AnimalModel) {
        tvAnimalName.text = "${animal.name}"
        val birthDateString = animal.birthDate
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val birthDate = formatter.parse(birthDateString)

        // Get the year from the birth date
        val birthCalendar = Calendar.getInstance().apply {
            time = birthDate
        }
        val birthYear = birthCalendar.get(Calendar.YEAR)

        // Set the age as the text of tvAnimalAge
        tvAnimalAge.text = birthYear.toString()
        tvAnimalStatus.text = "${animal.status}"
        tvAnimalGender.text = "${animal.gender}"
        tvAnimalLocation.text = "${animal.city}"
        tvAnimalDescription.text = "${animal.description}"
        tvAnimalLongitude.text = "${animal.longitude}"
        tvAnimalLatitude.text = "${animal.latitude}"

        // Set the image using Glide
        val imageUrl = animal.profileImageUrl
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .error(R.drawable.unknown_gender)
                .into(ivAnimalPhoto)
        } else {
            ivAnimalPhoto.setImageResource(R.drawable.unknown_gender)
        }
    }

    private fun startNewActivities() {

        visitCard.setOnClickListener {
            //Call visit
            ToastHandler.showToast(this, R.string.visit_saved)
        }

        shelterCard.setOnClickListener {
            //Call shelter
            ToastHandler.showToast(this, R.string.shelter_saved)
        }

        ibLike.setOnClickListener {
            //Call action for like
            ToastHandler.showToast(this, R.string.liked_successfully)
        }

        // Back action
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvAnimalLocation.setOnClickListener {

            val latitudeText = tvAnimalLatitude.text.toString()
            val longitudeText = tvAnimalLongitude.text.toString()
            val latitude = latitudeText.toDoubleOrNull()
            val longitude = longitudeText.toDoubleOrNull()
            if (latitude != null && longitude != null) {
                val intent = Intent(this, GoogleMapsActivity::class.java)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                intent.putExtra("name", tvAnimalLocation.text)
                startActivity(intent)
            } else {
                ToastHandler.showToast(this, R.string.latitude_longitude_error)
            }
        }
    }
}