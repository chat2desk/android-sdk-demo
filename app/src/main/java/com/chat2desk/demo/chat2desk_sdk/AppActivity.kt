package com.chat2desk.demo.chat2desk_sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.ui.Modifier
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.demo.chat2desk_sdk.components.AppTheme
import com.chat2desk.demo.chat2desk_sdk.screens.ChatScreen
import org.koin.android.ext.android.inject

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val c2d: IChat2Desk by inject()

                Box(
                    Modifier.padding(
                        WindowInsets.systemBars
                            .only(WindowInsetsSides.Start + WindowInsetsSides.End)
                            .asPaddingValues()
                    )
                ) {
                    ChatScreen(c2d = c2d, client = BuildConfig.CLIENT_TOKEN)
                }
            }
        }
    }

}