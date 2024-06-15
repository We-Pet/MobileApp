package com.ipca.wepet.data.repository.user

import android.util.Log
import com.google.gson.Gson
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.domain.repository.UserRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: WePetApi
) : UserRepository {

    override suspend fun getUser(
        email: String
    ): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteUser = try {
                val response = api.getUserByEmail(email)
                val jsonString = response.string()
                Log.i("User from API", jsonString)

                // Parse the JSON string
                val jsonResponse = JSONObject(jsonString)

                // Check if the "data" field is null
                if (jsonResponse.isNull("data")) {
                    emit(Resource.Error("User data not found"))
                    null
                } else {
                    // Extract user data from "data" field and parse it
                    val userData = jsonResponse.getJSONObject("data").toString()
                    val user = Gson().fromJson(userData, UserModel::class.java)
                    Log.i("UserModel from API", user.toString())
                    user
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }


            remoteUser?.let { user ->
                emit(Resource.Success(data = user))
            } ?: run {
                emit(Resource.Error("No user found"))
            }

            emit(Resource.Loading(false))
        }
    }

    override suspend fun updateUser(
        userId: String,
        image: MultipartBody.Part,
        name: String?,
        phoneNumber: String?,
        city: String?
    ): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteUser = try {
                // Create request body for name, phoneNumber, and city only if they are not null or blank
                val namePart = name?.toRequestBody("text/plain".toMediaTypeOrNull())
                val phoneNumberPart = phoneNumber?.toRequestBody("text/plain".toMediaTypeOrNull())
                val cityPart = city?.toRequestBody("text/plain".toMediaTypeOrNull())
                Log.i("updateUser", namePart.toString())
                Log.i("updateUser", phoneNumberPart.toString())
                Log.i("updateUser", cityPart.toString())


                // Call the API to update the user image and other fields
                val response =
                    api.updateUser(userId, image, namePart, phoneNumberPart, cityPart)
                val jsonString = response.string()
                val jsonResponse = JSONObject(jsonString)

                // Check if the "data" field is null
                val userData = jsonResponse.optJSONObject("data")
                Log.i("UserModel from API", userData.toString())
                if (userData == null) {
                    emit(Resource.Error("User data not found"))
                    null
                } else {
                    // Parse the user data and convert it to UserModel object
                    val user = Gson().fromJson(userData.toString(), UserModel::class.java)
                    emit(Resource.Success(data = user))
                    user
                }
            } catch (e: HttpException) {
                emit(Resource.Error("HTTP error: ${e.message()}"))
                null
            } catch (e: IOException) {
                emit(Resource.Error("Network error: ${e.message}"))
                null
            } catch (e: Exception) {
                emit(Resource.Error("Couldn't load data: ${e.message}"))
                null
            }

            emit(Resource.Loading(false))
        }
    }
}