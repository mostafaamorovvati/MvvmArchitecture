package com.example.gitapp

import android.app.Application
import com.example.gitapp.di.apiModule
import com.example.gitapp.di.repositoryModule
import com.example.gitapp.di.retrofitModule
import com.example.gitapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}