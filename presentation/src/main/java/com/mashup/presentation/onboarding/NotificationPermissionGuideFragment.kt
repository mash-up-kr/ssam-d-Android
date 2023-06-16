package com.mashup.presentation.onboarding

import androidx.compose.ui.Modifier
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.databinding.FragmentNotificationPermissionGuideBinding
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.ui.setThemeContent

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class NotificationPermissionGuideFragment :
    BaseFragment<FragmentNotificationPermissionGuideBinding>(
        R.layout.fragment_notification_permission_guide
    ) {

    override fun initViews() {
        binding.cpvTitle.setThemeContent {
            KeyLinkMintText(
                text = "내 행성으로 온 시그널을\n놓치지 않고 받아볼까요?",
                modifier = Modifier
            )
        }
    }
}