package com.ipca.wepet.presentation.fragment.event

sealed class EventListEvent {
    object Refresh : EventListEvent()
    data class OnSearchQueryChange(val query: String) : EventListEvent()
}