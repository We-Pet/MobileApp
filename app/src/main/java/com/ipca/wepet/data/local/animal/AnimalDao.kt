package com.ipca.wepet.data.local.animal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimals(
        userEntities: List<AnimalEntity>
    )

    @Query("DELETE FROM animalEntity")
    suspend fun clearAnimals()

    @Query("SELECT * FROM animalEntity WHERE id = :animalId")
    suspend fun getAnimalById(animalId: String): AnimalEntity?

    @Query("SELECT count(*) FROM animalEntity")
    suspend fun getAnimalCount(): Int?

    @Query(
        """
            SELECT * 
            FROM animalEntity
            WHERE LOWER(breed) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(city) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(gender) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(size) LIKE '%' || LOWER(:query) || '%' OR
                :query = ''

        """
    )
    suspend fun searchAnimalList(query: String): List<AnimalEntity>
}