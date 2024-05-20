package com.ipca.wepet.presentation.fragment.shelter

import com.ipca.wepet.domain.model.ShelterModel

data class ShelterListState(
    val shelters: List<ShelterModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
