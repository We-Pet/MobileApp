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

    fun updateUser(
        userId: String,
        image: MultipartBody.Part,
        name: String?,
        phoneNumber: String?,
        city: String?
    ) {
        Log.d("UserViewModel", "Updating user:")
        Log.d("UserViewModel", "userId: $userId")
        Log.d("UserViewModel", "name: $name")
        Log.d("UserViewModel", "phoneNumber: $phoneNumber")
        Log.d("UserViewModel", "city: $city")

        viewModelScope.launch {
            userRepository.updateUser(userId, image, name, phoneNumber, city).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            _userState.value =
                                _userState.value?.copy(user = user, isLoading = false, error = null)
                            Log.d("UserViewModel", "User updated successfully: $user")
                        }
                    }

                    is Resource.Error -> {
                        Log.e("UserViewModel", "Failed to update user: ${result.message}")
                        _userState.value = _userState.value?.copy(
                            error = result.message ?: "Unknown error",
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _userState.value = _userState.value?.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }


}
