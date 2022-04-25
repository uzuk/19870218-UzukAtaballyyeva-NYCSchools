package com.example.nycschools

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NYCSchoolsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@NYCSchoolsApplication)
            // declare modules
            modules(appModule)
        }
    }

    companion object {
        @get:Synchronized
        var instance: NYCSchoolsApplication? = null
            private set
    }
}

