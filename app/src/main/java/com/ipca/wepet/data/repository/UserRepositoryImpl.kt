package com.ipca.wepet.data.repository

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
    private val dao = db.dao

    override suspend fun getUsers(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<UserModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchUserList(query)
            emit(Resource.Success(
                data = localListings.map { it.toUserModel() }
            ))

            // Check if database is empty
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getAllUsers()
                val jsonString = response.string()
                val listings = Gson().fromJson(jsonString, Array<UserModel>::class.java).toList()
                listings

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { listings ->
                dao.clearUsers()
                dao.insertUsers(
                    listings.map { it.toUserEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchUserList("")
                        .map { it.toUserModel() }
                ))

                emit(Resource.Loading(false))
            }
        }
    }
}
