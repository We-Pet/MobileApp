package com.ipca.wepet.presentation.fragment.animal

import com.ipca.wepet.domain.model.AnimalModel

data class AnimalListState(
    val animals: List<AnimalModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
