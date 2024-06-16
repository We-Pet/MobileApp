package com.ipca.wepet.presentation.controller

import android.annotation.SuppressLint
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
import com.ipca.wepet.domain.model.ShelterModel
import com.ipca.wepet.presentation.fragment.FooterFragment
import com.ipca.wepet.util.ToastHandler
import java.io.Serializable

class ShelterActivity : AppCompatActivity() {
    private lateinit var ivShelterPhoto: ImageView
    private lateinit var tvShelterName: TextView
    private lateinit var tvShelterLocation: TextView
    private lateinit var tvShelterDescription: TextView
    private lateinit var tvShelterLongitude: TextView
    private lateinit var tvShelterLatitude: TextView


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

        val shelter = intent.getSerializableExtra("shelter") as? ShelterModel
        if (shelter != null) {
            showAnimalDetails(shelter)
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
        ivShelterPhoto = findViewById(R.id.IV_shelterPhoto)
        tvShelterName = findViewById(R.id.TV_shelterName)
        tvShelterLocation = findViewById(R.id.TV_shelterLocation)
        tvShelterDescription = findViewById(R.id.TV_shelterDescription)
        tvShelterLatitude = findViewById(R.id.TV_shelterLatitude)
        tvShelterLongitude = findViewById(R.id.TV_shelterLongitude)
        contactCard = findViewById(R.id.contact_card)
        animalCard = findViewById(R.id.animal_card)

    }

    @SuppressLint("SetTextI18n")
    private fun showAnimalDetails(shelter: ShelterModel) {
        tvShelterName.text = "${shelter.name}"
        tvShelterLocation.text = "${shelter.city} , ${shelter.address}"
        tvShelterDescription.text = "${shelter.description}"
        tvShelterLongitude.text = "${shelter.longitude}"
        tvShelterLatitude.text = "${shelter.latitude}"


        val imageUrl = shelter.profileImageUrl
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(imageUrl)
                .error(R.drawable.unknown_gender)
                .into(ivShelterPhoto)
        } else {
            ivShelterPhoto.setImageResource(R.drawable.unknown_gender)
        }
    }


    private fun startNewActivities() {

        contactCard.setOnClickListener {
            //Call contact
            ToastHandler.showToast(this, R.string.correct_pin_entered)
        }

        animalCard.setOnClickListener {
            //Call animal list with filters
            ToastHandler.showToast(this, R.string.animal_successfully)
        }

        ibLike.setOnClickListener {
            //Call action for like
            ToastHandler.showToast(this, R.string.liked_successfully)
        }

        // Back action
        ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        tvShelterLocation.setOnClickListener {
            val latitudeText = tvShelterLatitude.text.toString()
            val longitudeText = tvShelterLongitude.text.toString()
            val latitude = latitudeText.toDoubleOrNull()
            val longitude = longitudeText.toDoubleOrNull()
            if (latitude != null && longitude != null) {
                val intent = Intent(this, GoogleMapsActivity::class.java)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                intent.putExtra("name", tvShelterLocation.text)
                startActivity(intent)
            } else {
                ToastHandler.showToast(this, R.string.latitude_longitude_error)
            }
        }
    }
}