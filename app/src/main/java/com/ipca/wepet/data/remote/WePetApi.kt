package com.ipca.wepet.data.remote

import com.ipca.wepet.domain.model.UserModel
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface WePetApi {
    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String
    ): ResponseBody

    @PUT("users/{email}")
    suspend fun updateUserByEmail(
        @Path("email") email: String,
        @Body user: UserModel
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
        const val BASE_URL = "http://ec2-13-48-58-106.eu-north-1.compute.amazonaws.com:3000"
    }
}