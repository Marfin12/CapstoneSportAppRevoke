package com.example.capstonesportapprevoke.di

import com.example.capstonesportapprevoke.core.domain.usecase.SportInteractor
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideTourismUseCase(sportInteractor: SportInteractor): SportUseCase

}