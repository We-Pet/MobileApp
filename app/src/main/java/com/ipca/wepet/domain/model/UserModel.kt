package com.ipca.wepet.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserModel(
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("birth_date")
    val birthDate: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("profileImage")
    val profileImage: String? = null,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String? = null,
    @SerializedName("__v")
    val deleted: Int? = null
) : Serializable

data class UserResponse(
    @SerializedName("data")
    val data: UserModel
)