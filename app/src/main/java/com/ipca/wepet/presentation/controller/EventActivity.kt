package com.ipca.wepet.presentation.controller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R

class EventActivity : AppCompatActivity() {
    private lateinit var tvEventId: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_profil_layout)

        initializeElements()

        val eventId = intent.getStringExtra("event")
    }

    private fun initializeElements() {
        tvEventId = findViewById(R.id.TV_event_id)
    }

}