package com.ipca.wepet.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(
        userEntities: List<UserEntity>
    )

    @Query("DELETE FROM userEntity")
    suspend fun clearUsers()

    @Query("SELECT * FROM userEntity WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query(
        """
            SELECT * 
            FROM userEntity
            WHERE LOWER(email) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(name) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchUserList(query: String): List<UserEntity>
}