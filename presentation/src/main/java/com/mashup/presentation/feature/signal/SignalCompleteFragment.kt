package com.mashup.presentation.feature.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentSignalCompleteBinding
import com.mashup.presentation.feature.signal.compose.SignalCompleteScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class SignalCompleteFragment :
    BaseFragment<FragmentSignalCompleteBinding>(R.layout.fragment_signal_complete) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            SignalCompleteScreen(
                finishActivity = {
                    navigate(actionId = R.id.action_signalComplete_to_home)
                }
            )
        }
    }
}