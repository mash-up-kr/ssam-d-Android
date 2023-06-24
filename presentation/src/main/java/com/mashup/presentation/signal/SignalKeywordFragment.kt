package com.mashup.presentation.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentSignalKeywordBinding
import com.mashup.presentation.signal.compose.SignalKeywordScreen

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
                navigateUp = { navigateUp() }
            )
        }
    }
}