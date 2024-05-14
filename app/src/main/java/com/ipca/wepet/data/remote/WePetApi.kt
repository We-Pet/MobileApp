package com.ipca.wepet.data.remote

import com.ipca.wepet.domain.model.UserModel
import okhttp3.ResponseBody
import retrofit2.http.GET

interface WePetApi {
    @GET(":3002/users")
    suspend fun getAllUsers(
    ): ResponseBody

    @GET("animals")
    suspend fun getAllAnimals(
    ): ResponseBody
    companion object {
        const val BASE_URL = "http://ec2-16-171-132-76.eu-north-1.compute.amazonaws.com:3003"
    }
}