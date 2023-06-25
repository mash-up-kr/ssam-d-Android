package com.mashup.presentation.home

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentSubscribeKeywordBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/25
 */
class SubscribeKeywordFragment :
    BaseFragment<FragmentSubscribeKeywordBinding>(R.layout.fragment_subscribe_keyword) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            SubscribeKeywordScreen(
                navigateUp = { navigateUp() },
                navigateToHome = { navigate(R.id.action_subscribeKeyword_to_home) }
            )
        }
    }
}