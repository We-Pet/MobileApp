package com.ipca.wepet.presentation.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.presentation.fragment.FooterFragment

class NotificationSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animal_profil_layout)

        // Get the footer
        supportFragmentManager.beginTransaction()
            .replace(R.id.FL_footer, FooterFragment())
            .commit()

        //initializeElements()
        //startNewActivities()


    }

}