package com.example.capstonesportapprevoke

import android.app.Application
import com.example.capstonesportapprevoke.core.di.CoreComponent
import com.example.capstonesportapprevoke.core.di.DaggerCoreComponent
import com.example.capstonesportapprevoke.di.AppComponent
import com.example.capstonesportapprevoke.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}