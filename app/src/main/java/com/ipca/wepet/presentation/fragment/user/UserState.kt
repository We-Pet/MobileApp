package com.ipca.wepet.presentation.fragment.user

import com.ipca.wepet.domain.model.UserModel

data class UserState(
    val user: UserModel = UserModel(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val email: String = ""
)
