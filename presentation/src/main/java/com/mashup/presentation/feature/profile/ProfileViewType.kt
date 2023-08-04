package com.mashup.presentation.feature.profile

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */

interface BaseViewType {
    val description: String
}

@Immutable
sealed class ProfileViewType {
    data class UserInfo(
        val userImageUrl: String,
        val userName: String,
        val userEmail: String?
    ) : ProfileViewType()

    data class Header(
        override val description: String,
    ) : ProfileViewType(), BaseViewType

    data class NavigationContent(
        override val description: String,
        val userId: Long,
        val route: String,
        val color: Color = White
    ) : ProfileViewType(), BaseViewType

    data class NotificationContent(
        override val description: String,
        val isAgree: Boolean
    ) : ProfileViewType(), BaseViewType

    data class AppVersionContent(
        override val description: String,
        val appVersion: String
    ) : ProfileViewType(), BaseViewType

    data class LogoutContent(
        override val description: String,
    ) : ProfileViewType(), BaseViewType
}