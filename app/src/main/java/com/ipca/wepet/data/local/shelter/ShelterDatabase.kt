package com.ipca.wepet.data.local.shelter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ShelterEntity::class],
    version = 3,
)
abstract class ShelterDatabase : RoomDatabase() {
    abstract fun shelterDao(): ShelterDao

    companion object {
        @Volatile
        private var INSTANCE: ShelterDatabase? = null

        fun getDatabase(context: Context): ShelterDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseName = "shelterdb.db"
                val oldDatabase = context.getDatabasePath(databaseName)
                if (oldDatabase.exists()) {
                    oldDatabase.delete()
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShelterDatabase::class.java,
                    databaseName
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}