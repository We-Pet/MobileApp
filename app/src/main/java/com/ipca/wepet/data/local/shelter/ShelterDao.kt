package com.ipca.wepet.data.local.shelter

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShelterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShelters(
        userEntities: List<ShelterEntity>
    )

    @Query("DELETE FROM shelterEntity")
    suspend fun clearShelters()

    @Query("SELECT * FROM shelterEntity WHERE id = :shelterId")
    suspend fun getShelterById(shelterId: String): ShelterEntity?

    @Query("SELECT count(*) FROM shelterEntity")
    suspend fun getShelterCount(): Int?

    @Query(
        """
            SELECT * 
            FROM shelterEntity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(city) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(country) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(postalCode) LIKE '%' || LOWER(:query) || '%' OR
                :query = ''

        """
    )
    suspend fun searchShelterList(query: String): List<ShelterEntity>
}