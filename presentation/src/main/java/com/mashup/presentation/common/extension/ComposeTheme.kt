package com.mashup.presentation.common.extension

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.mashup.presentation.ui.theme.SsamDTheme

@Suppress("NOTHING_TO_INLINE")
internal inline fun ComponentActivity.setThemeContent(
    crossinline content: @Composable () -> Unit
) = setContent {
    SsamDTheme {
        content()
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun Fragment.setThemeContent(
    crossinline content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setContent {
        SsamDTheme {
            content()
        }
    }
}

internal inline fun ComposeView.setThemeContent(
    crossinline content: @Composable () -> Unit
) = setContent {
    SsamDTheme {
        content()
    }
}