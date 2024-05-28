package com.ipca.wepet.presentation.controller

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.presentation.fragment.FooterFragment
import com.ipca.wepet.presentation.fragment.HeaderFragment
import com.ipca.wepet.presentation.fragment.TabsFragment
import dagger.hilt.android.AndroidEntryPoint

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

        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_tabs, TabsFragment())
            .commit()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }
}