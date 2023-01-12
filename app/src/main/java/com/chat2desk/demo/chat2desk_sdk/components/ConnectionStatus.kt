package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat2desk.demo.chat2desk_sdk.R
import com.chat2desk.chat2desk_sdk.core.Chat2Desk
import com.chat2desk.chat2desk_sdk.core.datasource.services.State
import kotlinx.coroutines.launch

@Composable
fun ConnectionStatus(chat2Desk: Chat2Desk) {
    val coroutineScope = rememberCoroutineScope()
    val connectionStatus = chat2Desk.connectionStatus.collectAsState()
    fun stop() = coroutineScope.launch {
        chat2Desk.stop()
    }

    fun start() = coroutineScope.launch {
        chat2Desk.start()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Text("Connection status: ${connectionStatus.value.toString()}")
        Spacer(modifier = Modifier.width(8.dp))
        if (connectionStatus.value == State.CONNECTED) {
            IconButton(onClick = ::stop) {
                Icon(
                    Icons.Outlined.Cancel,
                    contentDescription = stringResource(id = R.string.stop),
                    tint = MaterialTheme.colors.error
                )
            }
        }
        if (connectionStatus.value == State.CLOSED) {
            IconButton(onClick = ::start) {
                Icon(
                    Icons.Outlined.PlayCircle,
                    contentDescription = stringResource(id = R.string.stop),
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}