package com.mashup.presentation.feature.profile

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.mashup.presentation.R
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
    fun getOptions(): List<ProfileViewType> {
        val options = mutableListOf<ProfileViewType>()
        options.apply {
            add(
                ProfileViewType.UserInfo(
                    id = 0,
                    userImageUrl = "",
                    userName = "아무개",
                    userEmail = "아무개@naver.com"
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 1,
                    description = context.getString(R.string.profile_sent_signal),
                    color = Mint
                )
            )
            add(
                ProfileViewType.Header(
                    id = 2,
                    description = context.getString(R.string.settings)
                )
            )
            add(
                ProfileViewType.NotificationContent(
                    id = 3,
                    description = context.getString(R.string.notification),
                )
            )
            add(
                ProfileViewType.Header(
                    id = 4,
                    description = context.getString(R.string.information)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 5,
                    description = context.getString(R.string.tos)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 6,
                    description = context.getString(R.string.privacy_policy),
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 7,
                    description = context.getString(R.string.open_source),
                )
            )
            add(
                ProfileViewType.AppVersionContent(
                    id = 8,
                    description = context.getString(R.string.app_version),
                    appVersion = getVersionName(),
                )
            )
            add(
                ProfileViewType.LogoutContent(
                    id = 9,
                    description = context.getString(R.string.logout)
                )
            )
        }
        return options.toList()
    }

    private fun getVersionName(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.applicationContext.packageManager.getPackageInfo(
                context.packageName, PackageManager.PackageInfoFlags.of(0L)
            ).versionName
        } else {
            context.applicationContext.packageManager.getPackageInfo(
                context.packageName, 0
            ).versionName
        }
    }
}