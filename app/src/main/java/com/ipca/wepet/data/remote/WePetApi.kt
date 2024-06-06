package com.ipca.wepet.data.remote

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path

interface WePetApi {
    @GET("users/getUserIDByEmail/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String
    ): ResponseBody

    @Multipart
    @PATCH("users/upload/image/{userId}")
    suspend fun updateUserImage(
        @Path("userId") userId: String,
        @Part image: MultipartBody.Part
    ): ResponseBody

    @GET("animals")
    suspend fun getAllAnimals(
    ): ResponseBody

    @GET("shelters")
    suspend fun getAllShelters(
    ): ResponseBody

    @GET("events")
    suspend fun getAllEvents(
    ): ResponseBody


    companion object {
        const val BASE_URL = "http://ec2-13-60-88-160.eu-north-1.compute.amazonaws.com:3000"
    }
}