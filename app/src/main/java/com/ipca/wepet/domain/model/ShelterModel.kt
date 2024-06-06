package com.ipca.wepet.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShelterModel(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("birth_date")
    val birthDate: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("postal_code")
    val postalCode: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("is_verified")
    val isVerified: Boolean?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("profileImage")
    val profileImage: String?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?,
    @SerializedName("__v")
    val deleted: Int?
) : Serializable

data class ShelterResponse(
    @SerializedName("data")
    val data: List<ShelterModel>
)