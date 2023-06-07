package com.mashup.presentation.mypage.profile

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.mashup.presentation.R

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
class Options(private val context: Context) {
    fun getOptions(): List<ProfileViewType> {
        val options = mutableListOf<ProfileViewType>()
        options.apply {
            add(
                ProfileViewType.UserInfo(
                    id = 0,
                    userName = "아무개",
                    userEmail = "아무개@naver.com",
                    userAvatarImage = "asdfasdfasdf"
                )
            )
            add(
                ProfileViewType.Header(
                    id = 1,
                    description = context.getString(R.string.settings)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 2,
                    actionId = R.id.action_profile_to_notificationSetting,
                    description = context.getString(R.string.notification),
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.Header(
                    id = 3,
                    description = context.getString(R.string.information)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 4,
                    actionId = R.id.action_profile_to_termsOfService,
                    description = context.getString(R.string.tos),
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 5,
                    actionId = R.id.action_profile_to_privacyPolicy,
                    description = context.getString(R.string.privacy_policy),
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 6,
                    actionId = R.id.action_profile_to_openSource,
                    description = context.getString(R.string.open_source),
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.AppVersionContent(
                    id = 7,
                    version = getVersionName(),
                )
            )
            add(
                ProfileViewType.LogoutContent(
                    id = 8
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