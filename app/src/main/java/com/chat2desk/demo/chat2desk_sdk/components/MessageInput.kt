package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat2desk.chat2desk_sdk.IChat2Desk
import com.chat2desk.demo.chat2desk_sdk.R
import com.chat2desk.demo.chat2desk_sdk.utils.AttachmentMeta
import com.chat2desk.demo.chat2desk_sdk.utils.toC2DAttachment
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageInput(
    chat2desk: IChat2Desk,
    attachmentSheetState: ModalBottomSheetState,
    attachment: AttachmentMeta?,
    clearAttachment: () -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var text by remember { mutableStateOf("") }
    var sending by remember { mutableStateOf(false) }

    attachment?.let {
        AttachmentPreview(
            meta = it,
            onRemove = clearAttachment
        )
    }

    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
        TextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    stringResource(id = R.string.send_message),
                    style = MaterialTheme.typography.body1,
                    color = textSecondary
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                trailingIconColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.primary,
            ),
            trailingIcon = {
                IconButton(
                    enabled = (text.isNotEmpty() || attachment != null) && !sending,
                    onClick = {
                        sending = true
                        val job = coroutineScope.launch {
                            attachment?.let {
                                chat2desk.sendMessage(
                                    text,
                                    it.toC2DAttachment(context)
                                )
                            } ?: chat2desk.sendMessage(text)
                        }
                        job.invokeOnCompletion {
                            text = ""
                            clearAttachment()
                            sending = false
                        }
                    }) {
                    Icon(
                        if (text.isNotEmpty()) Icons.Filled.Send else Icons.Outlined.Send,
                        contentDescription = stringResource(id = R.string.send_message),
                    )
                }
            },
            leadingIcon = {
                IconButton(
                    enabled = attachment == null,
                    onClick = { coroutineScope.launch { attachmentSheetState.show() } }) {
                    Icon(Icons.Outlined.AttachFile, contentDescription = null)
                }
            }
        )
    }
}