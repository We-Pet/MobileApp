package com.ipca.wepet.presentation.fragment.animal

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
import com.ipca.wepet.domain.model.AnimalModel

@Composable
fun AnimalList(animals: List<AnimalModel>?) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        animals?.let { animalList ->
            LazyColumn(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(animalList.chunked(3)) { rowOfAnimals ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowOfAnimals.forEach { animal ->
                            Box(modifier = Modifier.weight(1f)) {
                                AnimalListItem(
                                    animal = animal,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                        if (rowOfAnimals.size < 3) {
                            repeat(3 - rowOfAnimals.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

