package com.mashup.presentation.mypage.profile

import android.graphics.drawable.Drawable
import androidx.annotation.StyleRes

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
sealed class ProfileViewType {

    interface BaseViewType {
        val id: Int
    }

    data class UserInfo(
        override val id: Int,
        val userName: String,
        val userEmail: String,
        val userAvatarImage: String,
    ) : BaseViewType, ProfileViewType()

    data class Header(
        override val id: Int,
        val description: String,
    ) : BaseViewType, ProfileViewType()

    data class NotificationContent(
        override val id: Int,
        val description: String,
    ) : BaseViewType, ProfileViewType()

    data class NavigationContent(
        override val id: Int,
        val actionId: Int,
        val description: String,
        @StyleRes val textStyle: Int,
        val drawable: Drawable?,
    ) : BaseViewType, ProfileViewType()

    data class AppVersionContent(
        override val id: Int,
        val version: String,
    ) : BaseViewType, ProfileViewType()

    data class LogoutContent(
        override val id: Int,
    ) : BaseViewType, ProfileViewType()
}