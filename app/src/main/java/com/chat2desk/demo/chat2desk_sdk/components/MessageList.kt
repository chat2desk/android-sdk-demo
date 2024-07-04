package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.chat2desk.chat2desk_sdk.domain.entities.Message
import com.chat2desk.demo.chat2desk_sdk.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageList(
    modifier: Modifier,
    messages: List<Message>,
    onResend: (message: Message) -> Unit,
    onFetch: (loadMore: Boolean) -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(messages.size) {
        coroutineScope.launch {
            listState.animateScrollToItem(0)
        }
    }

    val state = rememberPullToRefreshState()

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            onFetch(false)
            state.endRefresh()
        }
    }

    Box(
        modifier
            .background(MessageListBackground)
            .nestedScroll(state.nestedScrollConnection)
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

            item {
                Box(
                    contentAlignment = Alignment.Center, modifier = modifier
                        .fillMaxWidth()
                ) {
                    if (messages.isEmpty()) {
                        Text(
                            text = "Empty list",
                            style = MaterialTheme.typography.titleMedium
                        )
                    } else {
                        Button(onClick = { onFetch(true) }) {
                            Text(
                                text = stringResource(id = R.string.load_more),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
        PullToRefreshContainer(state, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(name = "Messages List")
@Composable
fun MessageListPreview(@PreviewParameter(MessageListPreviewProvider::class) list: List<Message>) {
    MessageList(
        modifier = Modifier,
        messages = list,
        onResend = {},
        onFetch = {})
}

class MessageListPreviewProvider : PreviewParameterProvider<List<Message>> {
    override val values: Sequence<List<Message>> =
        sequenceOf(MessagePreviewParameterProvider().values.toList(), emptyList())
}

