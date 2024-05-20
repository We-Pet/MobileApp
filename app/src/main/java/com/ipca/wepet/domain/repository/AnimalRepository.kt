package com.ipca.wepet.domain.repository

import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {
    suspend fun getAnimals(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<AnimalModel>>>
}