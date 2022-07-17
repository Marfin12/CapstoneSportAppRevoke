package com.example.capstonesportapprevoke.factory

import android.content.Context
import com.example.capstonesportapprevoke.core.data.SportRepository
import com.example.capstonesportapprevoke.core.data.source.local.LocalDataSource
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDatabase
import com.example.capstonesportapprevoke.core.data.source.remote.RemoteDataSource
import com.example.capstonesportapprevoke.core.data.source.remote.network.ApiConfig
import com.example.capstonesportapprevoke.core.domain.repository.ISportRepository
import com.example.capstonesportapprevoke.core.domain.usecase.SportInteractor
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.core.utils.AppExecutors
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object Injection {
    private fun provideRepository(context: Context): ISportRepository {
        val database = SportDatabase.getInstance(context)
        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Yl6IYMBnxrwtn9DWo32vSutvY3gRn56iEfWj5/66c88=")
            .build()


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService(okHttpClient))
        val localDataSource = LocalDataSource.getInstance(database.sportDao())
        val appExecutors = AppExecutors()


        return SportRepository.getInstance(remoteDataSource, localDataSource, appExecutors, okHttpClient)
    }

    fun provideSportUseCase(context: Context): SportUseCase {
        val repository = provideRepository(context)
        return SportInteractor(repository)
    }
}
