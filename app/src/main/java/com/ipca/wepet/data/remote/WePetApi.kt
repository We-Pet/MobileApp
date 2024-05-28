package com.ipca.wepet.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface WePetApi {
    @GET("users")
    suspend fun getAllUsers(
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