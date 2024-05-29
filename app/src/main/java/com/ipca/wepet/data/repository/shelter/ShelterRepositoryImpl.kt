package com.ipca.wepet.data.repository.shelter

import android.util.Log
import com.google.gson.Gson
import com.ipca.wepet.data.local.shelter.ShelterDao
import com.ipca.wepet.data.local.shelter.ShelterDatabase
import com.ipca.wepet.data.mapper.shelter.toShelterEntity
import com.ipca.wepet.data.mapper.shelter.toShelterModel
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.ShelterModel
import com.ipca.wepet.domain.model.ShelterResponse
import com.ipca.wepet.domain.repository.ShelterRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShelterRepositoryImpl @Inject constructor(
    private val api: WePetApi,
    db: ShelterDatabase
) : ShelterRepository {
    private val dao: ShelterDao = db.shelterDao()

    override suspend fun getShelters(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<ShelterModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchShelterList(query)
            emit(Resource.Success(
                data = localListings.map { it.toShelterModel() }
            ))

            // Check if database is empty
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getAllShelters()
                val jsonString = response.string()
                Log.i("shelterList from API", jsonString)
                val gson = Gson()
                val shelterResponse = gson.fromJson(jsonString, ShelterResponse::class.java)
                val shelterList = shelterResponse.data
                shelterList
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { listings ->
                Log.i("Database shelter count", dao.getShelterCount().toString())
                dao.clearShelters()
                Log.i("ClearDatabase shelter count", dao.getShelterCount().toString())
                dao.insertShelters(
                    listings.map { it.toShelterEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchShelterList("")
                        .map { it.toShelterModel() }
                ))
                Log.i("shelterList in database", dao.searchShelterList("").size.toString())

                emit(Resource.Loading(false))
            }
        }
    }
}
