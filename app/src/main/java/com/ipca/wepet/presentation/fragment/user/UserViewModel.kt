package com.ipca.wepet.presentation.fragment.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.domain.repository.UserRepository
import com.ipca.wepet.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var state by mutableStateOf(UserState())

    init {
        getUserByEmail()
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.GetUser -> {
                getUserByEmail(state.email)
            }

            is UserEvent.UpdateUser -> {
                changeUserByEmail(state.user)
            }
        }
    }

    private fun getUserByEmail(
        email: String = state.email.lowercase() ?: ""
    ) {
        viewModelScope.launch {
            userRepository.getUser(email).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            state = state.copy(
                                user = user
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

    private fun changeUserByEmail(
        user: UserModel = state.user
    ) {
        viewModelScope.launch {
            userRepository.changeUser(user).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            state = state.copy(
                                user = user
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