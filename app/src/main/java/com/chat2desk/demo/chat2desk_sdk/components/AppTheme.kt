package com.chat2desk.demo.chat2desk_sdk.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Primary = Color(0xFF549CF0)
private val Secondary = Color(0xFF696969)
val MessageListBackground = Color(0xFFF5F7F8)
val outMessageBackground = Color(0xFFFFFFFF)
val inMessageBackground = Color(0xFFE0E9EF)
val HeaderBackground = Color(0xFFD2E0EB)
private val textPrimary = Color(0xFF3F3F3F)
val textSecondary = Color(0xFF888888)
private val textSubtitle = Color(0xFFB4B4B4)
private val Colors = lightColors(
    primary = Primary,
    secondary = Secondary,
    onPrimary = Color.White,
)
val typography = Typography(
    body1 = TextStyle(
        color = textPrimary,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    subtitle2 = TextStyle(
        color = textSubtitle,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        color = textSecondary,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    ),
)
val shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = Colors,
        typography = typography,
        shapes = shapes,
        content = {
            Surface(content = content)
        }
    )
}