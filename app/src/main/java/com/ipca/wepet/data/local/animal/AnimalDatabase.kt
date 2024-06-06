package com.ipca.wepet.data.local.animal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [AnimalEntity::class],
    version = 3,
)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getDatabase(context: Context): AnimalDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseName = "animaldb.db"
                val oldDatabase = context.getDatabasePath(databaseName)
                if (oldDatabase.exists()) {
                    oldDatabase.delete()
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    databaseName
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}