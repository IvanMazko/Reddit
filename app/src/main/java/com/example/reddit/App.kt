package com.example.reddit

import android.app.Application
import android.util.Log
import com.example.reddit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("AppInit", "onCreate called")
        koinInitialization()
    }

    private fun koinInitialization(){
        Log.d("KoinInit", "Koin is starting")
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
        Log.d("KoinInit", "Koin has started successfully!")
    }
}