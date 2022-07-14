package com.example.capstonesportapprevoke.core.di

import android.content.Context
import androidx.room.Room
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDao
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SportDatabase = Room.databaseBuilder(
        context,
        SportDatabase::class.java, "Tourism.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: SportDatabase): SportDao = database.sportDao()
}