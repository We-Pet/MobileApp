package com.ipca.wepet.data.remote.dto.animal

import com.squareup.moshi.Json
import java.time.LocalDateTime

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
    @field:Json(name = "__v") val deleted: Int
)

