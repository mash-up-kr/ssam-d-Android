package com.mashup.presentation.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.databinding.FragmentSignalKeywordBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/15
 */
class SignalKeywordFragment :
    BaseFragment<FragmentSignalKeywordBinding>(R.layout.fragment_signal_keyword) {

    override fun initViews() {
        binding.asd.setOnClickListener {
            navigate(R.id.action_signalKeyword_to_signalComplete)
        }
    }
}