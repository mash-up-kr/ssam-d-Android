package com.mashup.presentation.feature.onboarding

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>(R.layout.fragment_on_boarding) {
    override fun initViews() {
        binding.composeView.setThemeContent {
            OnBoardingScreen(navigateToNotificationPermission = ::navigateToNotification,
                finishActivity = { activity?.finish() })
        }
    }

    private fun navigateToNotification() {
        navigate(R.id.action_onBoardingFragment_to_notificationPermissionGuideFragment)
    }
}