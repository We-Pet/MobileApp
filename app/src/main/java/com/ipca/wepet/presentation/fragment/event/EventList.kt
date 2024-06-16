package com.ipca.wepet.presentation.fragment.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ipca.wepet.domain.model.EventModel

@Composable
fun EventList(events: List<EventModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(events) { event ->
                EventListItem(event = event)
            }
        }
    }
}