package com.ipca.wepet.presentation.fragment.user

import com.ipca.wepet.domain.model.UserModel
import okhttp3.MultipartBody

data class UserState(
    val user: UserModel = UserModel(),
    val userId: String = "",
    val image: MultipartBody.Part? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val email: String = ""
)
