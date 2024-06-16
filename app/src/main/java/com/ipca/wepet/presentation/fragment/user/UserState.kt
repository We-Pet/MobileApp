package com.ipca.wepet.presentation.fragment.user

import com.ipca.wepet.domain.model.UserModel

data class UserState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
