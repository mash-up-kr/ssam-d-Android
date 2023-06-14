package com.mashup.presentation.signal

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.databinding.FragmentSignalContentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class SignalContentFragment :
    BaseFragment<FragmentSignalContentBinding>(R.layout.fragment_signal_content) {

    override fun initViews() {
        binding.asd.setOnClickListener {
            navigate(R.id.action_signalContent_to_signalComplete)
        }
    }
}