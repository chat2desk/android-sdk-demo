package com.chat2desk.demo.chat2desk_sdk

import android.app.Application
import com.chat2desk.chat2desk_sdk.Chat2Desk
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.chat2desk_sdk.Settings
import com.chat2desk.chat2desk_sdk.create
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private val appModule = module {
        single<IChat2Desk> {
            val settings = Settings(
                authToken = BuildConfig.WIDGET_TOKEN,
                baseHost = BuildConfig.BASE_HOST,
                wsHost = BuildConfig.WS_HOST,
                storageHost = BuildConfig.STORAGE_HOST
            )
            settings.withLog = BuildConfig.DEBUG

            Chat2Desk.create(settings, get())
        }
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)

            androidContext(this@App)
            modules(appModule)
        }
    }
}