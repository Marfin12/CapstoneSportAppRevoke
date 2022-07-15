package com.example.capstonesportapprevoke.di

import com.example.capstonesportapprevoke.core.domain.usecase.SportInteractor
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideTourismUseCase(sportInteractor: SportInteractor): SportUseCase

}