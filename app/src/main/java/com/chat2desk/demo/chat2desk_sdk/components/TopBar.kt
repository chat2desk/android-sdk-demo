package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat2desk.demo.chat2desk_sdk.R
import com.chat2desk.chat2desk_sdk.core.Chat2Desk
import kotlinx.coroutines.launch

@Composable
fun AppBar(chat2Desk: Chat2Desk) {
    val operator = chat2Desk.operator.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }

    Surface(color = HeaderBackground, elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Operator(operator = operator.value, Modifier.weight(1f))
                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.MoreVert, null, tint = MaterialTheme.colors.primary)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(onClick = {
                            coroutineScope.launch {
                                chat2Desk.flushAll()
                                expanded = false
                            }
                        }) {
                            Text(text = stringResource(id = R.string.flush_all_data))
                        }
                        DropdownMenuItem(onClick = {
                            coroutineScope.launch {
                                chat2Desk.syncMessages()
                                expanded = false
                            }
                        }) {
                            Text(text = stringResource(id = R.string.fetch_messages))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            ConnectionStatus(chat2Desk)
        }
    }
}