package com.mashup.presentation.feature.chat

import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.compose.ChatScreen
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.extension.navigateUp
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
            ChatScreen(
                navigateToSendSignal = { navigate(R.id.action_chat_to_send_signal) },
                navigateToChatDetail = { navigate(R.id.action_chat_to_chatDetail) }
            )
        }
    }
}