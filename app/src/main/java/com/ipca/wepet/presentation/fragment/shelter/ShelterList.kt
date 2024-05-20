package com.ipca.wepet.presentation.fragment.shelter

import ShelterListItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipca.wepet.domain.model.ShelterModel

@Composable
fun ShelterList(shelters: List<ShelterModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        shelters.let { shelterList ->
            LazyColumn {
                items(shelterList) { shelter ->
                    ShelterListItem(shelter = shelter)
                }
            }
        }
    }
}