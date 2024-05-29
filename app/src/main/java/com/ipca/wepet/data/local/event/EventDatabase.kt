package com.ipca.wepet.data.local.event

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [EventEntity::class],
    version = 2,
)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseName = "eventdb.db"
                val oldDatabase = context.getDatabasePath(databaseName)
                if (oldDatabase.exists()) {
                    oldDatabase.delete()
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDatabase::class.java,
                    databaseName
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}