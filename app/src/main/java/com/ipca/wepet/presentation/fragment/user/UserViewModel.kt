package com.ipca.wepet.presentation.fragment.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipca.wepet.domain.repository.UserRepository
import com.ipca.wepet.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState

    init {
        _userState.value = UserState() // Initialize with default state
    }

    fun getUserByEmail(email: String = _userState.value?.user?.email ?: "") {
        viewModelScope.launch {
            userRepository.getUser(email).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            _userState.value = _userState.value?.copy(user = user)
                            Log.d("UserViewModel", "User fetched successfully: $user")
                        }
                    }

                    is Resource.Error -> {
                        Log.e("UserViewModel", "Failed to fetch user: ${result.message}")
                        _userState.value =
                            _userState.value?.copy(error = result.message ?: "Unknown error")
                        // Optionally, you can handle the error case further if needed
                    }

                    is Resource.Loading -> {
                        _userState.value = _userState.value?.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

    fun changeUserByEmail(userId: String, image: MultipartBody.Part) {
        viewModelScope.launch {
            userRepository.changeUser(userId, image).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            _userState.value = _userState.value?.copy(user = user)
                        }
                    }

                    is Resource.Error -> Unit // Handle error case
                    is Resource.Loading -> {
                        _userState.value = _userState.value?.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}
