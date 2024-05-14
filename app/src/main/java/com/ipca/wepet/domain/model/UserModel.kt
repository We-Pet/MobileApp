package com.ipca.wepet.domain.model

import java.time.LocalDateTime

data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val city: String,
    val deleted: Int,
    val birthDate: String,
    val phoneNumber: String,
    val updatedAt: String,
    val createdAt: String,
)
