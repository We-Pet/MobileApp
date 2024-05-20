package com.ipca.wepet.data.repository.event

import android.util.Log
import com.google.gson.Gson
import com.ipca.wepet.data.local.event.EventDao
import com.ipca.wepet.data.local.event.EventDatabase
import com.ipca.wepet.data.mapper.event.toEventEntity
import com.ipca.wepet.data.mapper.event.toEventModel
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.EventModel
import com.ipca.wepet.domain.model.EventResponse
import com.ipca.wepet.domain.repository.EventRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepositoryImpl @Inject constructor(
    private val api: WePetApi,
    db: EventDatabase
) : EventRepository {
    private val dao: EventDao = db.eventDao()

    override suspend fun getEvents(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<EventModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchEventList(query)
            emit(Resource.Success(
                data = localListings.map { it.toEventModel() }
            ))

            // Check if database is empty
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getAllEvents()
                val jsonString = response.string()
                Log.i("eventList from API", jsonString)
                val gson = Gson()
                val eventsResponse = gson.fromJson(jsonString, EventResponse::class.java)
                val eventList = eventsResponse.data
                eventList
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { listings ->
                Log.i("Database event count", dao.getEventCount().toString())
                dao.clearEvents()
                Log.i("ClearDatabase event count", dao.getEventCount().toString())
                dao.insertEvents(
                    listings.map { it.toEventEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchEventList("")
                        .map { it.toEventModel() }
                ))
                Log.i("eventList in database", dao.searchEventList("").size.toString())

                emit(Resource.Loading(false))
            }
        }
    }
}
