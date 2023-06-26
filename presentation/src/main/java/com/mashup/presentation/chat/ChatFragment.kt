package com.mashup.presentation.chat

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    override fun initViews() {
        binding.composeView.setThemeContent {

        }
    }
}