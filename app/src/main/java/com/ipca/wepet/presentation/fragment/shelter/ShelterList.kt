package com.ipca.wepet.presentation.fragment.shelter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        modifier = Modifier.fillMaxSize()
    ) {
        shelters.let { shelterList ->
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(shelterList.chunked(3)) { rowOfShelters ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowOfShelters.forEach { shelter ->
                            Box(modifier = Modifier.weight(1f)) {
                                ShelterListItem(
                                    shelter = shelter,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                        if (rowOfShelters.size < 3) {
                            repeat(3 - rowOfShelters.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}