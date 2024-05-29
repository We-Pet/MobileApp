package com.ipca.wepet.data.local.event

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(
        userEntities: List<EventEntity>
    )

    @Query("DELETE FROM eventEntity")
    suspend fun clearEvents()

    @Query("SELECT * FROM eventEntity WHERE id = :eventId")
    suspend fun getEventById(eventId: String): EventEntity?

    @Query("SELECT count(*) FROM eventEntity")
    suspend fun getEventCount(): Int?

    @Query(
        """
            SELECT * 
            FROM eventEntity
            WHERE :query = ''

        """
    )
    suspend fun searchEventList(query: String): List<EventEntity>
}