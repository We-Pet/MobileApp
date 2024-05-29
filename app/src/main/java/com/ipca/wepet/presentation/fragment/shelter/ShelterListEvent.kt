package com.ipca.wepet.presentation.fragment.shelter

sealed class ShelterListEvent {
    object Refresh : ShelterListEvent()
    data class OnSearchQueryChange(val query: String) : ShelterListEvent()
}