package com.example.capstonesportapprevoke.core.di

import com.example.capstonesportapprevoke.core.data.SportRepository
import com.example.capstonesportapprevoke.core.domain.repository.ISportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(sportRepository: SportRepository): ISportRepository

}