package com.ipca.wepet.data.local.shelter

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShelterEntity(
    @PrimaryKey @NonNull val id: String,
    val name: String?,
    val description: String?,
    val email: String?,
    val birthDate: String?,
    val phoneNumber: String?,
    val country: String?,
    val city: String?,
    val postalCode: String?,
    val address: String?,
    val isVerified: Boolean?,
    val createdAt: String?,
    val userId: String?,
    val deleted: Int = 0
)
