package com.ipca.wepet.domain.repository

import com.ipca.wepet.domain.model.EventModel
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvents(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<EventModel>>>
}