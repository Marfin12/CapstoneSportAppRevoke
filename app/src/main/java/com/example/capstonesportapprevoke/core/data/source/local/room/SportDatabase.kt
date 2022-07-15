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
}