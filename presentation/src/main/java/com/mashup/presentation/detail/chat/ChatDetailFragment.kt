package com.mashup.presentation.detail.chat

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentChatDetailBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
class ChatDetailFragment : BaseFragment<FragmentChatDetailBinding>(R.layout.fragment_chat_detail) {
    override fun initViews() {
        binding.composeView.setThemeContent {

        }
    }
}