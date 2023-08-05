package com.mashup.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mashup.presentation.navigation.KeyLinkNavHost
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Black
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KeyLinkApp(
    appState: KeyLinkAppState = rememberKeyLinkAppState()
) {
    val coroutineScope = rememberCoroutineScope()
    var currentBottomSheetType by remember { mutableStateOf(BottomSheetType.IDLE) }

    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            when (currentBottomSheetType) {
                BottomSheetType.CHAT_CONNECTED -> KeyLinkConnectedBottomSheet(
                    modifier = Modifier.fillMaxWidth()
                )
                BottomSheetType.SIGNAL_ZONE -> KeyLinkSignalZoneBottomSheet(
                    modifier = Modifier.fillMaxWidth()
                )
                else -> Unit
            }
        },
        modalSheetState = appState.modalBottomSheetState
    ) {
        Scaffold(
            bottomBar = {
                if (appState.isBottomBarVisible()) {
                    KeyLinkBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
            backgroundColor = Black,
            snackbarHost = { KeyLinkSnackBar(snackBarHostState = appState.scaffoldState.snackbarHostState) },
            scaffoldState = appState.scaffoldState
        ) { innerPadding ->
            KeyLinkNavHost(
                appState = appState,
                modifier = Modifier.padding(innerPadding),
                onShowSnackbar = { message, duration ->
                    appState.showSnackbar(
                        message = message,
                        duration = duration
                    )
                },
                onShowBottomSheet = { bottomSheetType ->
                    currentBottomSheetType = bottomSheetType
                    appState.showBottomSheet()
                },
                onBackClick = {
                    coroutineScope.launch {
                        if (appState.modalBottomSheetState.isVisible) appState.modalBottomSheetState.hide()
                        else appState.navController.navigateUp()
                    }
                }
            )
        }
    }
}

enum class BottomSheetType {
    IDLE, CHAT_CONNECTED, SIGNAL_ZONE
}
