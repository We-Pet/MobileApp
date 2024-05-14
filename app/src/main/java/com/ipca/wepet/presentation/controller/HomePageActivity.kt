package com.ipca.wepet.presentation.controller

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.ipca.wepet.R
import com.ipca.wepet.data.repository.AnimalRepositoryImpl
import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.domain.repository.AnimalRepository
import com.ipca.wepet.presentation.fragment.FooterFragment
import com.ipca.wepet.presentation.fragment.HeaderFragment
import com.ipca.wepet.presentation.fragment.SearchFragment
import com.ipca.wepet.presentation.fragment.TabsFragment
import com.ipca.wepet.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {

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

        // Get the recyclerview to pass to the tabs fragment
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_tabs, TabsFragment(recyclerView))
            .commit()

        // Get the search
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_search, SearchFragment())
            .commit()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }
}