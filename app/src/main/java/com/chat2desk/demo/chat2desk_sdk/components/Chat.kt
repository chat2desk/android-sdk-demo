package com.chat2desk.demo.chat2desk_sdk.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chat2desk.demo.chat2desk_sdk.utils.AttachmentMeta
import com.chat2desk.demo.chat2desk_sdk.utils.getFileMetaData
import com.chat2desk.chat2desk_sdk.core.Chat2Desk
import com.chat2desk.chat2desk_sdk.core.domain.entities.Message
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chat(chat2desk: Chat2Desk) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val messages = chat2desk.messages.collectAsState(initial = listOf())

    fun resendMessage(message: Message) = coroutineScope.launch {
        chat2desk.resendMessage(message)
    }

    val attachmentSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    var attachment by remember {
        mutableStateOf<AttachmentMeta?>(null)
    }

    fun handleAttachmentSelect(uri: Uri?) = coroutineScope.launch {
        uri?.let { attachment = getFileMetaData(context, it) }

        attachmentSheetState.hide()
    }

    fun onClearAttachment() {
        attachment = null
    }

    LaunchedEffect(Unit) {
        chat2desk.start()
        chat2desk.syncMessages()
    }
    ModalBottomSheetLayout(
        sheetState = attachmentSheetState,
        sheetShape = MaterialTheme.shapes.medium,
        sheetContent = {
            AttachmentModal(onSelect = ::handleAttachmentSelect)
        }
    ) {
        Column {
            MessageList(Modifier.weight(1f), messages.value, ::resendMessage) {
                chat2desk.syncMessages()
            }

            MessageInput(chat2desk, attachmentSheetState, attachment, ::onClearAttachment)
        }
    }
}