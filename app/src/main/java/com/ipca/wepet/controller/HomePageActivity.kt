package com.ipca.wepet.controller

import AnimalAdapter
import SearchFragment
import TabsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.models.AnimalModel

class HomePageActivity : AppCompatActivity() {
    private lateinit var animalsView: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalList: List<AnimalModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_footer, FooterFragment())
            .commit()

        // Get the header
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_header, HeaderFragment())
            .commit()

        // Get the tabs
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_tabs, TabsFragment())
            .commit()

        // Get the search
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_search, SearchFragment())
            .commit()



        animalsView = findViewById(R.id.recyclerView)
        animalList = generateData()
        animalAdapter = AnimalAdapter(animalList)

        animalsView.layoutManager = GridLayoutManager(this, 3)
        animalsView.adapter = animalAdapter
    }


    private fun generateData(): List<AnimalModel> {
        val animalList = mutableListOf<AnimalModel>()
        for (i in 1..12) {
            val animal = AnimalModel("Animal $i", "Race $i", "Sex $i")
            animalList.add(animal)
        }
        return animalList
    }


}