package com.ipca.wepet.presentation.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipca.wepet.presentation.fragment.animal.AnimalList
import com.ipca.wepet.presentation.fragment.animal.AnimalListEvent
import com.ipca.wepet.presentation.fragment.animal.AnimalViewModel
import com.ipca.wepet.presentation.fragment.event.EventList
import com.ipca.wepet.presentation.fragment.shelter.ShelterList

@Composable
fun AnimalListScreen(
    animalViewModel: AnimalViewModel,
    shelterViewModel: ShelterViewModel,
    eventViewModel: EventViewModel
) {
    var selectedList by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        when (selectedList) {
            0 -> animalViewModel.onEvent(AnimalListEvent.Refresh)
            1 -> shelterViewModel.onEvent(ShelterListEvent.Refresh)
            2 -> eventViewModel.onEvent(EventListEvent.Refresh)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { selectedList = 0 },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text("Animals")
            }
            Button(
                onClick = { selectedList = 1 },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text("Shelters")
            }
            Button(
                onClick = { selectedList = 2 },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Events")
            }
        }

        when (selectedList) {
            0 -> AnimalList(animalViewModel.state.animals ?: emptyList())
            1 -> ShelterList(shelterViewModel.state.shelters ?: emptyList())
            2 -> EventList(eventViewModel.state.events ?: emptyList())
        }
    }
}






