package com.ipca.wepet.data.remote.dto.shelter

import com.squareup.moshi.Json

data class ShelterDTO(
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "birth_date") val birthDate: String?,
    @field:Json(name = "phone_number") val phoneNumber: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "city") val city: String?,
    @field:Json(name = "postal_code") val postalCode: String?,
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "is_verified") val isVerified: Boolean?,
    @field:Json(name = "createdAt") val createdAt: String?,
    @field:Json(name = "user_id") val userId: String?,
    @field:Json(name = "latitude") val latitude: String?,
    @field:Json(name = "longitude") val longitude: String?,
    @field:Json(name = "profileImage") val profileImage: String?,
    @field:Json(name = "profileImageUrl") val profileImageUrl: String?,
    @field:Json(name = "__v") val deleted: Int?
)

