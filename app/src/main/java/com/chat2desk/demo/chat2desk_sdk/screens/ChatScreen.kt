package com.chat2desk.demo.chat2desk_sdk.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.demo.chat2desk_sdk.components.AppBar
import com.chat2desk.demo.chat2desk_sdk.components.Chat

@Composable
fun ChatScreen(c2d: IChat2Desk, client: String?) {
    val snackbarHostState = remember { SnackbarHostState() }
    val error = c2d.error
        .collectAsState(null)

    LaunchedEffect(error.value) {
        error.value?.let {
            it.message?.let { message ->
                snackbarHostState.showSnackbar(
                    message
                )
                Log.e("App", message, it)
            }
        }
    }
    Scaffold(
        topBar = {
            AppBar(c2d, client)
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Chat(c2d, client)
        }
    }
}