package com.ipca.wepet.controller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.models.AnimalModel

class AnimalActivity : AppCompatActivity() {
    private lateinit var tvAnimalName: TextView
    private lateinit var tvAnimalRace: TextView
    private lateinit var tvAnimalSex: TextView
    private lateinit var tvAnimalId: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animal_profil_layout)

        initializeElements()

        val animalId = intent.getStringExtra("animal")
        animalId?.let { getAnimalInfo(animalId) }
    }

    private fun initializeElements() {
        tvAnimalId = findViewById(R.id.TV_animal_id)
        tvAnimalName = findViewById(R.id.TV_animal_name)
        tvAnimalRace = findViewById(R.id.TV_animal_race)
        tvAnimalSex = findViewById(R.id.TV_animal_sex)
    }

    private fun getAnimalInfo(animalId: String) {
        // Get animal from database

        val animal = AnimalModel(animalId, "Tom", "Cat", "Male")
        showAnimalDetails(animal)
    }

    private fun showAnimalDetails(animal: AnimalModel) {
        tvAnimalId.text = "Id: ${animal.id}"
        tvAnimalName.text = "Name: ${animal.name}"
        tvAnimalRace.text = "Race: ${animal.race}"
        tvAnimalSex.text = "Sex: ${animal.sex}"
    }
}