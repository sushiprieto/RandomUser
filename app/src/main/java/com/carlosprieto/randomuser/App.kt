package com.carlosprieto.randomuser

import android.app.Application
import com.carlosprieto.randomuser.data.module.ApiModule
import com.carlosprieto.randomuser.data.module.UsersModule
import com.carlosprieto.randomuser.data.module.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                arrayListOf(
                    ApiModule,
                    UsersModule,
                    ViewModelModule
                )
            )
        }
    }
}