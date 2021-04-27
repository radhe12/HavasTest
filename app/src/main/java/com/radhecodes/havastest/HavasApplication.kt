package com.radhecodes.havastest

import android.app.Application
import com.radhecodes.havastest.di.appModule
import com.radhecodes.havastest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HavasApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HavasApplication)
            modules(appModule, viewModelModule)
        }
    }
}