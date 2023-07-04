package com.mashup.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.presentation.KeyLinkAppState
import com.mashup.presentation.feature.home.navigation.homeGraph

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkNavHost(
    appState: KeyLinkAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph()
    }
}