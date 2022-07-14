package com.example.capstonesportapprevoke.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstonesportapprevoke.core.data.source.local.entity.CountryEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.SportEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [SportEntity::class, TeamEntity::class, CountryEntity::class], version = 3, exportSchema = false)
abstract class SportDatabase : RoomDatabase() {
    abstract fun sportDao(): SportDao

    companion object {
        @Volatile
        private var INSTANCE: SportDatabase? = null
        private val passphrase: ByteArray = SQLiteDatabase.getBytes("capstone".toCharArray())
        private val factory = SupportFactory(passphrase)

        fun getInstance(context: Context): SportDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportDatabase::class.java,
                    "Tourism.db"
                )
                    .fallbackToDestructiveMigration()
                    .openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                instance
            }
    }
}