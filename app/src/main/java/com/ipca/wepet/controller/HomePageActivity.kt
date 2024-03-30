package com.ipca.wepet.controller

import TabsFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_footer, FooterFragment())
            .commit()

        // Get the header
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_header, HeaderFragment())
            .commit()

        // Get the tabs
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_tabs, TabsFragment())
            .commit()
    }
}