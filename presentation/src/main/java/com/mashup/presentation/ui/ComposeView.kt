package com.mashup.presentation.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.mashup.presentation.ui.theme.SsamDTheme

inline fun ComponentActivity.setThemeContent(
    crossinline content: @Composable () -> Unit
) = setContent {
    SsamDTheme {
        content()
    }
}

inline fun Fragment.setThemeContent(
    crossinline content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    setContent {
        SsamDTheme {
            content()
        }
    }
}
