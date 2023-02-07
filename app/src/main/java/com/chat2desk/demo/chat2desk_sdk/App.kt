package com.chat2desk.demo.chat2desk_sdk

import android.app.Application
import com.chat2desk.chat2desk_sdk.AttachedFile
import com.chat2desk.chat2desk_sdk.Chat2Desk
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.chat2desk_sdk.Settings
import com.chat2desk.chat2desk_sdk.create
import com.chat2desk.chat2desk_sdk.domain.entities.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                storageHost = BuildConfig.STORAGE_HOST,
                withLog =  BuildConfig.DEBUG
            )
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


class SomeClass(private val chat2desk: IChat2Desk) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    init {
        coroutineScope.launch {
            chat2desk.messages.collect { messages ->
                println(messages)
            }
        }
    }
    public suspend fun sendMessage(txt: String) {
        chat2desk.sendMessage(txt)
    }

    public suspend fun sendMessage(txt: String, attachment: AttachedFile) {
        chat2desk.sendMessage(txt, attachment)
    }

    public suspend fun resendMessage(message: Message) {
        chat2desk.resendMessage(message)
    }
}