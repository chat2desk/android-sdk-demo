package com.chat2desk.demo.chat2desk_sdk.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chat2desk.demo.chat2desk_sdk.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AttachmentModal(onSelect: (uri: Uri?) -> Unit) {
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia(), onSelect)

    val pickDocument =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument(), onSelect)

    Column(modifier = Modifier.padding(10.dp)) {
        ListItem(
            modifier = Modifier.clickable {
                pickMedia.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageAndVideo
                    )
                )
            },
            text = { Text(stringResource(id = R.string.select_media_attachment)) },
            icon = {
                Icon(
                    Icons.Filled.Theaters,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            })
        Divider()
        ListItem(
            modifier = Modifier.clickable { pickDocument.launch(arrayOf("*/*")) },
            text = { Text(stringResource(id = R.string.select_file_attachment)) },
            icon = {
                Icon(
                    Icons.Filled.Description,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        )
    }
}