package com.mashup.presentation.feature.profile.compose

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.feature.profile.ProfileViewModel
import com.mashup.presentation.feature.profile.ProfileViewType
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
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val optionsList by viewModel.optionsList.collectAsStateWithLifecycle()
    var isChecked by rememberSaveable { mutableStateOf(false) }

    ProfileScreen(
        modifier = modifier,
        optionsList = optionsList,
        isChecked = isChecked,
        onCheckedChange = {
            isChecked = it
            viewModel.toggleNotificationSwitch(it)
        },
        onEditClick = {},
        onBackClick = onBackClick
    )
}

@Composable
private fun ProfileScreen(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
    optionsList: List<ProfileViewType>,
    modifier: Modifier = Modifier,
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
        ProfileContent(
            modifier = Modifier.padding(innerPaddingValues),
            optionsList = optionsList,
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            onEditClick = onEditClick
        )
    }
}

@Composable
fun ProfileContent(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onEditClick: () -> Unit,
    optionsList: List<ProfileViewType>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(optionsList) { index, viewType ->
            when (index) {
                2, 4 -> { Spacer(modifier = Modifier.height(20.dp)) }
                8 -> { Spacer(modifier = Modifier.height(52.dp)) }
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
                    LogoutContent(description = viewType.description)
                }
                is ProfileViewType.NavigationContent -> {
                    NavigationContent(
                        description = viewType.description,
                        onNavigateClick = {}
                    )
                }
                is ProfileViewType.NotificationContent -> {
                    NotificationContent(
                        isChecked = isChecked,
                        onCheckedChange = onCheckedChange,
                        description = viewType.description
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(260.dp))
        }
    }
}
