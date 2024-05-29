package com.ipca.wepet.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(
        userEntities: UserEntity
    )

    @Query("DELETE FROM userEntity")
    suspend fun clearUsers()

    @Query("SELECT * FROM userEntity WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM userEntity WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query(
        """
        UPDATE userEntity 
        SET name = :name, 
            email = :newEmail, 
            address = :address, 
            password = :password, 
            phoneNumber = :phone 
        WHERE email = :currentEmail
    """
    )
    suspend fun updateUserDetails(
        currentEmail: String,
        name: String,
        newEmail: String,
        address: String,
        password: String,
        phone: String
    ): Int
}