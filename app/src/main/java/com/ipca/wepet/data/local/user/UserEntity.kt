package com.ipca.wepet.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val city: String,
    val deleted: Int,
    val birthDate: String,
    val phoneNumber: String,
    val updatedAt: String,
    val createdAt: String
)
