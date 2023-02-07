package com.chat2desk.demo.chat2desk_sdk

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.demo.chat2desk_sdk.components.AppBar
import com.chat2desk.demo.chat2desk_sdk.components.AppTheme
import com.chat2desk.demo.chat2desk_sdk.components.ChatScreen
import org.koin.android.ext.android.inject

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val c2d: IChat2Desk by inject()
                val scaffoldState = rememberScaffoldState()
                val error = c2d.error
                    .collectAsState(null)

                LaunchedEffect(error.value) {
                    error.value?.let {
                        it.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(
                                message
                            )
                            Log.e("App", message, it)
                        }
                    }
                }

                Box(
                    Modifier.padding(
                        WindowInsets.systemBars
                            .only(WindowInsetsSides.Start + WindowInsetsSides.End)
                            .asPaddingValues()
                    )
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = { AppBar(c2d) },
                        snackbarHost = {
                            SnackbarHost(it) { data ->
                                Snackbar {
                                    Text(data.message, color = Color.White)
                                }
                            }
                        }
                    ) {
                        Column(modifier = Modifier.padding(it)) {
                            ChatScreen(c2d)
                        }
                    }
                }
            }
        }
    }

}