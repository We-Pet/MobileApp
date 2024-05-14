package com.ipca.wepet.presentation.fragment.event

import EventListItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipca.wepet.domain.model.EventModel

@Composable
fun EventList(events: List<EventModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        LazyColumn {
            items(events) { event ->
                EventListItem(event = event)
            }
        }
    }
}