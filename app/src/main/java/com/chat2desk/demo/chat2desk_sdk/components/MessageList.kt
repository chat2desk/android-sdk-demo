package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.chat2desk.chat2desk_sdk.domain.entities.Message
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageList(
    modifier: Modifier,
    messages: List<Message>,
    onResend: (message: Message) -> Unit,
    onRefresh: suspend () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(messages.size) {
        coroutineScope.launch {
            listState.animateScrollToItem(0)
        }
    }

    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = coroutineScope.launch {
        refreshing = true
        onRefresh()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    Box(
        modifier
            .background(MessageListBackground)
            .pullRefresh(state)
            .fillMaxWidth()
    ) {
        LazyColumn(
            state = listState,
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            reverseLayout = true,
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                items = messages,
                key = { it.id }
            ) { message ->
                MessageItem(message = message, onResend = {
                    onResend(message)
                })
            }


            if (messages.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center, modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Empty list",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(name = "Messages List")
@Composable
fun MessageListPreview(@PreviewParameter(MessageListPreviewProvider::class) list: List<Message>) {
    MessageList(modifier = Modifier, messages = list, onResend = {}, onRefresh = {})
}

class MessageListPreviewProvider : PreviewParameterProvider<List<Message>> {
    override val values: Sequence<List<Message>> =
        sequenceOf(MessagePreviewParameterProvider().values.toList(), emptyList())
}

