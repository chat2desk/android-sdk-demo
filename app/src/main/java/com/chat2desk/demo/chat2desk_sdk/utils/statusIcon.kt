package com.chat2desk.demo.chat2desk_sdk.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Schedule
import com.chat2desk.chat2desk_sdk.core.domain.entities.DeliveryStatus

fun statusIcon(status: DeliveryStatus) = when (status) {
    DeliveryStatus.SENDING -> Icons.Filled.Schedule
    DeliveryStatus.SENT -> Icons.Filled.Done
    DeliveryStatus.NOT_DELIVERED -> Icons.Filled.Close
    else -> Icons.Filled.DoneAll
}