package com.ipca.wepet.presentation.fragment.animal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipca.wepet.domain.model.AnimalModel

@Composable
fun AnimalList(animals: List<AnimalModel>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        animals?.let { animalList ->
            LazyColumn {
                items(animalList) { animal ->
                    AnimalListItem(animal = animal)
                }
            }
        }
    }
}