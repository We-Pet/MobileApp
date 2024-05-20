package com.ipca.wepet.domain.repository

import com.ipca.wepet.domain.model.ShelterModel
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShelterRepository {
    suspend fun getShelters(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<ShelterModel>>>
}