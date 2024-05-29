package com.ipca.wepet.presentation.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.ipca.wepet.R
import com.ipca.wepet.presentation.fragment.animal.AnimalList
import com.ipca.wepet.presentation.fragment.animal.AnimalListEvent
import com.ipca.wepet.presentation.fragment.animal.AnimalViewModel
import com.ipca.wepet.presentation.fragment.event.EventList
import com.ipca.wepet.presentation.fragment.event.EventListEvent
import com.ipca.wepet.presentation.fragment.event.EventViewModel
import com.ipca.wepet.presentation.fragment.shelter.ShelterList
import com.ipca.wepet.presentation.fragment.shelter.ShelterListEvent
import com.ipca.wepet.presentation.fragment.shelter.ShelterViewModel


@Composable
fun ListScreen(
    animalViewModel: AnimalViewModel,
    shelterViewModel: ShelterViewModel,
    eventViewModel: EventViewModel
) {
    var selectedList by remember { mutableIntStateOf(0) }
    val comfortaaFontFamily = FontFamily(Font(R.font.comfortaa_regular))
    val mainBlue = colorResource(id = R.color.main_blue)

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
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { selectedList = 0 },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tabs_gray)),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = if (selectedList == 0) R.drawable.tab_animals_icon_blue else R.drawable.tab_animal_icon_black),
                                contentDescription = stringResource(id = R.string.tab_animals),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = R.string.animals),
                                color = if (selectedList == 0) mainBlue else Color.Black,
                                fontFamily = comfortaaFontFamily
                            )
                            if (selectedList == 0) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Divider(
                                    color = mainBlue,
                                )
                            }
                        }
                    }
                    Button(
                        onClick = { selectedList = 1 },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tabs_gray)),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = if (selectedList == 1) R.drawable.tab_shelter_icon_blue else R.drawable.tab_shelter_icon_black),
                                contentDescription = stringResource(id = R.string.tab_for_shelter),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = R.string.shelters),
                                color = if (selectedList == 1) mainBlue else Color.Black,
                                fontFamily = comfortaaFontFamily
                            )
                            if (selectedList == 1) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Divider(
                                    color = mainBlue,
                                )
                            }
                        }
                    }
                    Button(
                        onClick = { selectedList = 2 },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tabs_gray)),
                        modifier = Modifier
                            .weight(1f)
                            .height(80.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = if (selectedList == 2) R.drawable.tab_animals_icon_blue else R.drawable.tab_animal_icon_black),
                                contentDescription = stringResource(id = R.string.tab_for_events),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = stringResource(id = R.string.events),
                                color = if (selectedList == 2) mainBlue else Color.Black,
                                fontFamily = comfortaaFontFamily
                            )
                            if (selectedList == 2) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Divider(
                                    color = mainBlue,
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = when (selectedList) {
                        0 -> animalViewModel.state.searchQuery
                        1 -> shelterViewModel.state.searchQuery
                        else -> eventViewModel.state.searchQuery
                    },
                    onValueChange = { newValue ->
                        val event: Any = when (selectedList) {
                            0 -> AnimalListEvent.OnSearchQueryChange(newValue)
                            1 -> ShelterListEvent.OnSearchQueryChange(newValue)
                            else -> EventListEvent.OnSearchQueryChange(newValue)
                        }
                        when (selectedList) {
                            0 -> animalViewModel.onEvent(event as AnimalListEvent)
                            1 -> shelterViewModel.onEvent(event as ShelterListEvent)
                            else -> eventViewModel.onEvent(event as EventListEvent)
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    },
                    maxLines = 1,
                    singleLine = true
                )

                when (selectedList) {
                    0 -> AnimalList(animalViewModel.state.animals)
                    1 -> ShelterList(shelterViewModel.state.shelters)
                    2 -> EventList(eventViewModel.state.events)
                }
            }
        }
    }
}





