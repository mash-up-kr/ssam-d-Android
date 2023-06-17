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
                ProfileViewType.NavigationContent(
                    id = 1,
                    actionId = R.id.action_profile_to_sendSignalList,
                    description = context.getString(R.string.profile_sent_signal),
                    textStyle = R.style.KeyLinkTextAppearance_Body1_Mint,
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
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
                    actionId = R.id.action_profile_to_termsOfService,
                    description = context.getString(R.string.tos),
                    textStyle = R.style.KeyLinkTextAppearance_Body1,
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 6,
                    actionId = R.id.action_profile_to_privacyPolicy,
                    description = context.getString(R.string.privacy_policy),
                    textStyle = R.style.KeyLinkTextAppearance_Body1,
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.NavigationContent(
                    id = 7,
                    actionId = R.id.action_profile_to_openSource,
                    description = context.getString(R.string.open_source),
                    textStyle = R.style.KeyLinkTextAppearance_Body1,
                    drawable = ContextCompat.getDrawable(context, R.drawable.ic_chevron_right_24)
                )
            )
            add(
                ProfileViewType.AppVersionContent(
                    id = 8,
                    version = getVersionName(),
                )
            )
            add(
                ProfileViewType.LogoutContent(
                    id = 9
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