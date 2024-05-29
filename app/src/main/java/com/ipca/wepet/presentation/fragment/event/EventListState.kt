package com.ipca.wepet.presentation.fragment.event

import com.ipca.wepet.domain.model.EventModel

data class EventListState(
    val events: List<EventModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
