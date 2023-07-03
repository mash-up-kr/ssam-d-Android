package com.mashup.presentation.detail.message

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentMessageDetailBinding
import com.mashup.presentation.detail.message.compose.MessageDetailScreen

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
class MessageDetailFragment :
    BaseFragment<FragmentMessageDetailBinding>(R.layout.fragment_message_detail) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            MessageDetailScreen(
                onUpButtonClick = { navigateUp() },
                onMenuClick = { /* 신고하기 */ },
                onSendReplyClick = { /* 답장하기 */ }
            )
        }
    }
}