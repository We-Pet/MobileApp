package com.ipca.wepet.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val city: String,
    val address: String,
    val password: String,
    val deleted: Int,
    val birthDate: String,
    val phoneNumber: String,
    val createdAt: String
)
