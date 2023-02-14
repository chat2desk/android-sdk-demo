package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.chat2desk_sdk.datasource.services.ConnectionState
import com.chat2desk.demo.chat2desk_sdk.R
import kotlinx.coroutines.launch

@Composable
fun ConnectionStatus(chat2Desk: IChat2Desk) {
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
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text("Connection status: ${connectionStatus.value.toString()}")
        Spacer(modifier = Modifier.width(8.dp))
        if (connectionStatus.value == ConnectionState.CONNECTED) {
            IconButton(
                onClick = ::stop,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
            ) {
                Icon(
                    Icons.Outlined.Pause,
                    contentDescription = stringResource(id = R.string.stop),
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        if (connectionStatus.value == ConnectionState.CLOSED) {
            IconButton(
                onClick = ::start,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = stringResource(id = R.string.stop),
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}