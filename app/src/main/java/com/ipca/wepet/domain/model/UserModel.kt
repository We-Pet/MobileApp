package com.ipca.wepet.domain.model

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
