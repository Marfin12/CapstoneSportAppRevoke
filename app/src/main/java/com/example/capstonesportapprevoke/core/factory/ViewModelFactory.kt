package com.example.capstonesportapprevoke.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.detail.DetailViewModel
import com.example.capstonesportapprevoke.di.AppScope
import com.example.capstonesportapprevoke.favorite.FavoriteViewModel
import com.example.capstonesportapprevoke.home.HomeViewModel
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}