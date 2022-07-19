package com.example.capstonesportapprevoke.newFeature

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.core.factory.Injection.provideSportUseCase

class ViewModelFactory private constructor(private val sportUseCase: SportUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    provideSportUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(NewFeatureViewModel::class.java) -> {
                NewFeatureViewModel(sportUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}