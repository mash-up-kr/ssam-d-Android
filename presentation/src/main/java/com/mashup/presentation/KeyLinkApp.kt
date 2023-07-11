package com.mashup.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mashup.presentation.navigation.KeyLinkNavHost
import com.mashup.presentation.ui.common.KeyLinkBottomBar
import com.mashup.presentation.ui.common.KeyLinkSnackBar
import com.mashup.presentation.ui.theme.Black

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkApp(
    appState: KeyLinkAppState = rememberKeyLinkAppState()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        bottomBar = {
            if (appState.isBottomBarVisible()) {
                KeyLinkBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier
                )
            }
        },
        backgroundColor = Black,
        snackbarHost = { KeyLinkSnackBar(snackBarHostState = snackbarHostState) }
    ) { innerPadding ->
        KeyLinkNavHost(
            appState = appState,
            modifier = Modifier.padding(innerPadding),
            onShowSnackbar = { message, action ->
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = action,
                    duration = SnackbarDuration.Short
                ) == SnackbarResult.ActionPerformed
            }
        )
    }
}