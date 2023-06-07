package com.mashup.presentation.mypage.profile

import androidx.annotation.DrawableRes

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
sealed class ProfileViewType {

    interface BaseViewType {
        val id: Int
    }

    interface ClickableViewType {
        val onClick: () -> Unit
    }

    data class UserInfo(
        override val id: Int,
        override val onClick: () -> Unit,
        val userName: String,
        val userEmail: String,
        val userAvatarImage: String,
    ) : BaseViewType, ClickableViewType, ProfileViewType()

    data class Header(
        override val id: Int,
        val description: String,
    ) : BaseViewType, ProfileViewType()

    data class NavigationContent(
        override val id: Int,
        override val onClick: () -> Unit,
        val description: String,
        @DrawableRes val drawableRes: Int,
    ) : BaseViewType, ClickableViewType, ProfileViewType()

    data class AppVersionContent(
        override val id: Int,
        val version: String,
    ) : BaseViewType, ProfileViewType()

    data class LogoutContent(
        override val id: Int,
        override val onClick: () -> Unit,
    ) : BaseViewType, ClickableViewType, ProfileViewType()
}