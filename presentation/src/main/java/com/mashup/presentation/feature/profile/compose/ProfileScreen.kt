package com.mashup.presentation.feature.profile.compose

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.common.extension.findActivity
import com.mashup.presentation.feature.login.LoginActivity
import com.mashup.presentation.feature.profile.ProfileViewModel
import com.mashup.presentation.feature.profile.ProfileViewType
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.common.KeyLinkLogoutDialog
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
    onNavigateClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ProfileScreen(
        modifier = modifier,
        onCheckedChange = {
            viewModel.toggleNotificationSwitch(it)
        },
        onEditClick = {},
        onBackClick = onBackClick,
        onNavigateClick = onNavigateClick,
        onLogoutClick = {
            viewModel.logout()
            context.startActivity(Intent(context, LoginActivity::class.java))
            context.findActivity().finish()
        },
        uiState = uiState
    )
}

@Composable
private fun ProfileScreen(
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
    onNavigateClick: (String) -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
    uiState: ProfileViewModel.UiState
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = onBackClick
            )
        }
    ) { innerPaddingValues ->
        when (uiState) {
            ProfileViewModel.UiState.Loading -> {
                KeyLinkLoading()
            }
            is ProfileViewModel.UiState.Success -> {

                ProfileContent(
                    modifier = Modifier.padding(innerPaddingValues),
                    optionsList = uiState.profileList,
                    onCheckedChange = onCheckedChange,
                    onEditClick = onEditClick,
                    onNavigateClick = onNavigateClick,
                    onLogoutClick = onLogoutClick
                )
            }
            is ProfileViewModel.UiState.Failure -> {
                // TODO: fail 화면
            }
        }
    }
}

@Composable
fun ProfileContent(
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onNavigateClick: (String) -> Unit,
    onLogoutClick: () -> Unit,
    optionsList: List<ProfileViewType>,
    modifier: Modifier = Modifier,
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(optionsList) { index, viewType ->
            when (index) {
                2, 4 -> {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                8 -> {
                    Spacer(modifier = Modifier.height(52.dp))
                }
            }

            when (viewType) {
                is ProfileViewType.UserInfo -> {
                    UserInfoContent(
                        userImageUrl = viewType.userImageUrl,
                        userName = viewType.userName,
                        userEmail = viewType.userEmail,
                        onEditClick = onEditClick
                    )
                }
                is ProfileViewType.Header -> {
                    Header(
                        description = viewType.description
                    )
                }
                is ProfileViewType.AppVersionContent -> {
                    AppVersionContent(
                        description = viewType.description,
                        appVersion = viewType.appVersion
                    )
                }
                is ProfileViewType.LogoutContent -> {
                    LogoutContent(
                        description = viewType.description,
                        onClick = { showDialog = true }
                    )
                }
                is ProfileViewType.NavigationContent -> {
                    NavigationContent(
                        description = viewType.description,
                        route = viewType.route,
                        onNavigateClick = onNavigateClick
                    )
                }
                is ProfileViewType.NotificationContent -> {
                    var isChecked by rememberSaveable { mutableStateOf(viewType.isAgree) }

                    NotificationContent(
                        isChecked = isChecked,
                        onCheckedChange = {
                            onCheckedChange(it)
                            isChecked = it
                        },
                        description = viewType.description
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(260.dp))
        }
    }

    if (showDialog) {
        KeyLinkLogoutDialog(
            onDismissRequest = { showDialog = false },
            onCloseClick = { showDialog = false },
            onLogoutClick = onLogoutClick
        )
    }
}
