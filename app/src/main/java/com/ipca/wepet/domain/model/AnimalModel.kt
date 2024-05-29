package com.ipca.wepet.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimalModel(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("birth_date")
    val birthDate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("shelter_id")
    val shelterId: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("animal_type")
    val animalType: String?,
    @SerializedName("breed")
    val breed: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("__v")
    val deleted: Int?
) : Serializable

data class AnimalResponse(
    @SerializedName("data")
    val data: List<AnimalModel>
)