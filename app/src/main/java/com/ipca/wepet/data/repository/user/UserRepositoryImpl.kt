package com.ipca.wepet.data.repository.user

import android.util.Log
import com.google.gson.Gson
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.domain.repository.UserRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import org.json.JSONObject
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

    override suspend fun changeUser(
        userId: String,
        image: MultipartBody.Part
    ): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteUser = try {
                val response = api.updateUserImage(userId, image)
                val jsonString = response.string()
                val user = Gson().fromJson(jsonString, UserModel::class.java)
                user
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
}
