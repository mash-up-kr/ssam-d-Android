package com.mashup.presentation.feature.profile

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.getAppVersion
import com.mashup.presentation.feature.profile.model.ProfileUiModel
import com.mashup.presentation.navigation.KeyLinkNavigationRoute
import com.mashup.presentation.ui.theme.Mint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
@ViewModelScoped
class ProfileOptionsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getOptions(profileUiModel: ProfileUiModel): List<ProfileViewType> {
        val options = mutableListOf<ProfileViewType>()
        options.apply {
            add(
                ProfileViewType.UserInfo(
                    userEmail = profileUiModel.email,
                    userName = profileUiModel.nickname,
                    userImageUrl = profileUiModel.profileImageUrl
                )
            )
            add(
                ProfileViewType.Header(
                    description = context.getString(R.string.information)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    description = context.getString(R.string.tos),
                    route = KeyLinkNavigationRoute.ProfileGraph.TermsOfServiceRoute.route
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    description = context.getString(R.string.privacy_policy),
                    route = KeyLinkNavigationRoute.ProfileGraph.PrivacyPolicyRoute.route
                )
            )
            add(
                ProfileViewType.AppVersionContent(
                    description = context.getString(R.string.app_version),
                    appVersion = context.getAppVersion(),
                )
            )
            add(
                ProfileViewType.LogoutContent(
                    description = context.getString(R.string.logout)
                )
            )
        }
        return options.toList()
    }
}