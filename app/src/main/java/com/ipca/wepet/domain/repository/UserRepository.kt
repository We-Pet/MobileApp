package com.ipca.wepet.domain.repository

import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
interface UserRepository {
    suspend fun getUsers(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<UserModel>>>
}