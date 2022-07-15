package com.example.capstonesportapprevoke.di

import com.example.capstonesportapprevoke.core.domain.usecase.SportInteractor
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.detail.DetailViewModel
import com.example.capstonesportapprevoke.favorite.FavoriteViewModel
import com.example.capstonesportapprevoke.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> { SportInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}