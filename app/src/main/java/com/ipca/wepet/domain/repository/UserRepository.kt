package com.ipca.wepet.domain.repository

import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(
        email: String
    ): Flow<Resource<UserModel>>

    suspend fun changeUser(
        user: UserModel
    ): Flow<Resource<UserModel>>
}