package com.example.reddit.presentation

import android.app.Application
import com.example.reddit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        koinInitialization()
    }

    private fun koinInitialization(){
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}