package com.mashup.presentation.signal

import androidx.compose.ui.res.stringResource
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.databinding.FragmentSignalCompleteBinding
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.common.extension.setThemeContent
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
        with(binding) {
            tvTitle.setThemeContent {
                KeyLinkMintText(text = stringResource(id = R.string.send_complete))
            }

            btnClose.setOnClickListener {
                requireActivity().finish()
            }
        }
    }
}