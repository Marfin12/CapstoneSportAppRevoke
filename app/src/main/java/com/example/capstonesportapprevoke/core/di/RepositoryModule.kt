package com.example.capstonesportapprevoke.core.di

import android.content.Context
import androidx.room.Room
import com.example.capstonesportapprevoke.core.data.SportRepository
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDao
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDatabase
import com.example.capstonesportapprevoke.core.data.source.remote.network.ApiService
import com.example.capstonesportapprevoke.core.domain.repository.ISportRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(sportRepository: SportRepository): ISportRepository

}