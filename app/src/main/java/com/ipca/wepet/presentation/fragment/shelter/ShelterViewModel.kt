package com.ipca.wepet.presentation.fragment.shelter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipca.wepet.domain.repository.ShelterRepository
import com.ipca.wepet.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShelterViewModel @Inject constructor(
    private val shelterRepository: ShelterRepository
) : ViewModel() {

    var state by mutableStateOf(ShelterListState())

    private var searchJob: Job? = null

    init {
        getShelterList()
    }

    fun onEvent(event: ShelterListEvent) {
        when (event) {
            is ShelterListEvent.Refresh -> {
                getShelterList(fetchFromRemote = true)
            }

            is ShelterListEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getShelterList()
                }
            }
        }
    }

    private fun getShelterList(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            shelterRepository
                .getShelters(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    shelters = listings
                                )
                            }
                        }

                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}