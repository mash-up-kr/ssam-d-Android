package com.mashup.presentation.feature.profile

import androidx.compose.ui.graphics.Color

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */

interface BaseViewType {
    val id: String
    val description: String
}

sealed class ProfileViewType {
    data class UserInfo(
        override val id: String,
        override val description: String,
        val userName: String,
        val userEmail: String
    ) : ProfileViewType(), BaseViewType

    data class Header(
        override val id: String,
        override val description: String,
    ) : ProfileViewType(), BaseViewType

    data class NavigationContent(
        override val id: String,
        override val description: String,
        val color: Color
    ) : ProfileViewType(), BaseViewType

    data class NotificationContent(
        override val id: String,
        override val description: String,
    ) : ProfileViewType(), BaseViewType

    data class AppVersionContent(
        override val id: String,
        override val description: String,
        val appVersion: String
    ) : ProfileViewType(), BaseViewType

    data class LogoutContent(
        override val id: String,
        override val description: String,
    ) : ProfileViewType(), BaseViewType
}