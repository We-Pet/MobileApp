package com.ipca.wepet.data.local.animal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimalEntity(
    @PrimaryKey val id: String,
    val name: String?,
    val birthDate: String?,
    val description: String?,
    val gender: String?,
    val userId: String?,
    val shelterId: String?,
    val city: String?,
    val size: String?,
    val animalType: String?,
    val breed: String?,
    val createdAt: String?,
    val latitude: String?,
    val longitude: String?,
    val status: String?,
    val profileImage: String?,
    val profileImageUrl: String?,
    val deleted: Int = 0
)
