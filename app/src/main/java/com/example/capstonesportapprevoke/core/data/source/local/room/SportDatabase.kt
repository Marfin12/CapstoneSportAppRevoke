package com.example.capstonesportapprevoke.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonesportapprevoke.core.data.source.local.entity.CountryEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.SportEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity

@Database(entities = [SportEntity::class, TeamEntity::class, CountryEntity::class], version = 3, exportSchema = false)
abstract class SportDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao

    companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null

        fun getInstance(context: Context): SportDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportDatabase::class.java,
                    "Tourism.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}