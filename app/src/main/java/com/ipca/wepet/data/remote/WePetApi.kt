package com.ipca.wepet.data.remote

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Path

interface WePetApi {

    /**  Users endPoints **/
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


    /**  Animals endPoints **/
    @GET("animals")
    suspend fun getAllAnimals(
    ): ResponseBody


    /**  Shelters endPoints **/
    @GET("shelters")
    suspend fun getAllShelters(
    ): ResponseBody


    /**  Events endPoints **/
    @GET("events")
    suspend fun getAllEvents(
    ): ResponseBody

    companion object {
        const val BASE_URL = "http://ec2-13-48-1-181.eu-north-1.compute.amazonaws.com:3000"
    }
}