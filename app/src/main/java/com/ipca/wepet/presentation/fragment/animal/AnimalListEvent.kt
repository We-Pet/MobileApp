package com.ipca.wepet.presentation.fragment.animal

sealed class AnimalListEvent {
    object Refresh: AnimalListEvent()
    data class OnSearchQueryChange(val query: String): AnimalListEvent()
}