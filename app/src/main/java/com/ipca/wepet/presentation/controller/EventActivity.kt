package com.ipca.wepet.presentation.controller

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.EventModel

class EventActivity : AppCompatActivity() {
    private lateinit var tvEventId: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_profil_layout)

        initializeElements()

        val eventId = intent.getStringExtra("event")
        eventId?.let { getEventInfo(eventId) }
    }

    private fun initializeElements() {
        tvEventId = findViewById(R.id.TV_event_id)
    }

    private fun getEventInfo(eventId: String) {
        // Get animal from database

        val event = EventModel(eventId, "Tom", "Cat", "Male")
        showEventDetails(event)
    }

    private fun showEventDetails(event: EventModel) {
        tvEventId.text = "Id: ${event.id}"
    }
}