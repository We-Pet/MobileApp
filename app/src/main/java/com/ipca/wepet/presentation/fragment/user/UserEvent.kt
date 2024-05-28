package com.ipca.wepet.presentation.fragment.user

import com.ipca.wepet.domain.model.UserModel

sealed class UserEvent {
    data class GetUser(val email: String) : UserEvent()

    data class UpdateUser(val user: UserModel) : UserEvent()
}