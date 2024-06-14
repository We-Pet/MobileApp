package com.ipca.wepet.presentation.fragment.user

import okhttp3.MultipartBody

sealed class UserEvent {
    data class GetUser(val email: String) : UserEvent()
    data class UpdateUser(val userId: String, val image: MultipartBody.Part) : UserEvent()
}
