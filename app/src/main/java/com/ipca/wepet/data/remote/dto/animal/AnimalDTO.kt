package com.ipca.wepet.data.remote.dto.animal

import com.squareup.moshi.Json

data class AnimalDTO(
    @field:Json(name = "_id") val id: String,
    val name: String,
    @field:Json(name = "birth_date") val birthDate: String,
    val description: String,
    val gender: String,
    @field:Json(name = "user_id") val userId: String,
    @field:Json(name = "shelter_id") val shelterId: String,
    val city: String,
    val size: String,
    @field:Json(name = "animal_type") val animalType: String,
    val breed: String,
    @field:Json(name = "createdAt") val createdAt: String,
    @field:Json(name = "latitude") val latitude: String?,
    @field:Json(name = "longitude") val longitude: String?,
    @field:Json(name = "profileImage")  val profileImage: String?,
    @field:Json(name = "profileImageUrl") val profileImageUrl: String?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "__v") val deleted: Int
)

