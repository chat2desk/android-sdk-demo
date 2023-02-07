package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.runtime.Composable
import com.chat2desk.chat2desk_sdk.Chat2Desk
import com.chat2desk.chat2desk_sdk.IChat2Desk

@Composable
fun ChatScreen(chat2desk: IChat2Desk) {
    Chat(chat2desk)
}