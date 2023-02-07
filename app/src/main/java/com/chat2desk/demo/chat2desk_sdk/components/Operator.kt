package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.chat2desk.demo.chat2desk_sdk.R
import com.chat2desk.chat2desk_sdk.domain.entities.Operator

@Composable
fun Operator(operator: Operator?, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = operator?.avatar,
            contentDescription = null,
            contentScale = ContentScale.None,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(modifier = Modifier.size(30.dp), contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                    )
                }
            } else {
                SubcomposeAsyncImageContent(contentScale = ContentScale.Crop)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Top) {
            Text(
                text = operator?.name
                    ?: stringResource(id = R.string.waiting_operator),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (operator?.typing == true) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(id = R.string.operator_typing),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}