package com.ipca.wepet.data.local.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseName = "userdb.db"
                val oldDatabase = context.getDatabasePath(databaseName)
                if (oldDatabase.exists()) {
                    oldDatabase.delete()
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    databaseName
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}