package com.mashup.presentation.feature.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentSignalKeywordBinding
import com.mashup.presentation.feature.signal.compose.SignalKeywordScreen

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/15
 */
class SignalKeywordFragment :
    BaseFragment<FragmentSignalKeywordBinding>(R.layout.fragment_signal_keyword) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            SignalKeywordScreen(
                isLoading = false,
                navigateUp = { navigateUp() },
                navigateToComplete = { navigate(R.id.action_signalKeyword_to_signalComplete) }
            )
        }
    }
}