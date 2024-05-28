package com.ipca.wepet.data.repository.user

import com.google.gson.Gson
import com.ipca.wepet.data.local.user.UserDatabase
import com.ipca.wepet.data.mapper.user.toUserEntity
import com.ipca.wepet.data.mapper.user.toUserModel
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.domain.repository.UserRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: WePetApi,
    private val db: UserDatabase
) : UserRepository {
    private val dao = db.userDao()

    override suspend fun getUser(
        email: String
    ): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading(true))
            val localUser = dao.getUserByEmail(email)
            emit(
                Resource.Success(
                    data = localUser?.toUserModel()
                )
            )

            // Check if database is empty
            val isDbEmpty = localUser == null
            if (!isDbEmpty ) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getUserByEmail(email)
                val jsonString = response.string()
                val user = Gson().fromJson(jsonString, UserModel::class.java)
                user
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { user ->
                dao.clearUsers()
                dao.insertUsers(
                    user.toUserEntity()
                )
                emit(Resource.Success(data = user))

                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun changeUser(
        userModel: UserModel
    ): Flow<Resource<UserModel>> {
        return flow {
            emit(Resource.Loading(true))
            val localUser = userModel.email?.let { dao.getUserByEmail(it) }
            emit(
                Resource.Success(
                    data = localUser?.toUserModel()
                )
            )

            // Check if database is empty
            val isDbEmpty = localUser == null
            if (isDbEmpty) {
                emit(Resource.Error("Database is empty!"))
                return@flow
            }
            val remoteListings = try {
                val response = userModel.email?.let { api.updateUserByEmail(it, userModel) }
                val jsonString = response?.string()
                val user = Gson().fromJson(jsonString, UserModel::class.java)
                user
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { user ->
                dao.clearUsers()
                dao.insertUsers(
                    user.toUserEntity()
                )
                emit(Resource.Success(data = user))
                emit(Resource.Loading(false))
            }
        }
    }
}
