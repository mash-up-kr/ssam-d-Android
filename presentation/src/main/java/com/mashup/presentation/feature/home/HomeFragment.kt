package com.mashup.presentation.feature.home

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentHomeBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            HomeScreen(
                navigateToSubscribeKeyword = { navigate(R.id.action_home_to_subscribeKeyword)},
                navigateToGuide = {navigate(R.id.action_homeFragment_to_guideFragment)}
            )
        }
    }
}