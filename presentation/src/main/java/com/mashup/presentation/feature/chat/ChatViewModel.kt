package com.mashup.presentation.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.chat.GetChatInfoUseCase
import com.mashup.domain.usecase.chat.GetChatsParam
import com.mashup.domain.usecase.chat.GetChatsUseCase
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailUiState
import com.mashup.presentation.feature.detail.chat.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatInfoUseCase: GetChatInfoUseCase,
    private val getChatsUseCase: GetChatsUseCase
): ViewModel() {

    private val _chatDetailUiState: MutableStateFlow<ChatDetailUiState> = MutableStateFlow(ChatDetailUiState.Loading)
    val chatDetailUiState = _chatDetailUiState.asStateFlow()

    fun getChatInfoAndChats(id: Long, pageNo: Int, pageLength: Int = PAGE_LENGTH) {
        viewModelScope.launch {
            val param = GetChatsParam(id, pageNo, pageLength)
            getChatInfoUseCase.execute(id)
                .zip(getChatsUseCase.execute(param)) { chatInfo, chats ->
                    chatInfo.toUiModel(chats)
                }.catch {
                    Timber.e("채팅 상세 get 실패.")
                    _chatDetailUiState.value = ChatDetailUiState.Failure(it.message)
                }.collect { chatDetailUiModel ->
                    Timber.i("채팅 상세 get 성공~!")
                    _chatDetailUiState.value = ChatDetailUiState.Success(chatDetailUiModel)
                }
        }
    }

    companion object {
        const val PAGE_LENGTH = 10
    }
}