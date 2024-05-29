package com.ipca.wepet.data.remote.dto.user

import com.squareup.moshi.Json

data class UserDTO(
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "city") val city: String,
    @field:Json(name = "__v") val deleted: Int,
    @field:Json(name = "birth_date") val birthDate: String,
    @field:Json(name = "phoneNumber") val phoneNumber: String,
    @field:Json(name = "updatedAt") val updatedAt: String,
    @field:Json(name = "createdAt") val createdAt: String
)

