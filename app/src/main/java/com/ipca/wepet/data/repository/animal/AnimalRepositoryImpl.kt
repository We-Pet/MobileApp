package com.ipca.wepet.data.repository.animal

import android.util.Log
import com.google.gson.Gson
import com.ipca.wepet.data.local.animal.AnimalDao
import com.ipca.wepet.data.local.animal.AnimalDatabase
import com.ipca.wepet.data.mapper.animal.toAnimalEntity
import com.ipca.wepet.data.mapper.animal.toAnimalModel
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.domain.model.AnimalModel
import com.ipca.wepet.domain.model.AnimalResponse
import com.ipca.wepet.domain.repository.AnimalRepository
import com.ipca.wepet.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalRepositoryImpl @Inject constructor(
    private val api: WePetApi,
    db: AnimalDatabase
) : AnimalRepository {
    private val dao: AnimalDao = db.animalDao()

    override suspend fun getAnimals(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<AnimalModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchAnimalList(query)
            emit(Resource.Success(
                data = localListings.map { it.toAnimalModel() }
            ))

            // Check if database is empty
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getAllAnimals()
                val jsonString = response.string()
                Log.i("AnimalList from API", jsonString)
                val gson = Gson()
                val animalResponse = gson.fromJson(jsonString, AnimalResponse::class.java)
                val animalList = animalResponse.data
                animalList
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            // Clear, insert in database, and search from database
            remoteListings?.let { listings ->
                Log.i("Database animal count", dao.getAnimalCount().toString())
                dao.clearAnimals()
                Log.i("ClearDatabase animal count", dao.getAnimalCount().toString())
                dao.insertAnimals(
                    listings.map { it.toAnimalEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchAnimalList("")
                        .map { it.toAnimalModel() }
                ))
                Log.i("AnimalList in database", dao.searchAnimalList("").size.toString())

                emit(Resource.Loading(false))
            }
        }
    }
}
