package com.mashup.presentation.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetChatsUseCase
import com.mashup.presentation.detail.chat.compose.ChatDetailUiState
import com.mashup.presentation.detail.chat.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val getChatsUseCase: GetChatsUseCase
): ViewModel() {

    private val _chatDetailUiState: MutableStateFlow<ChatDetailUiState> = MutableStateFlow(ChatDetailUiState.Loading)
    val chatDetailUiState = _chatDetailUiState.asStateFlow()

    fun getChats(id: Long) {
        viewModelScope.launch {
            getChatsUseCase.execute(id)
                .onSuccess { chatDetail ->
                    Timber.i("채팅 상세 get 성공~!")
                    _chatDetailUiState.emit(ChatDetailUiState.Success(chatDetail.toUiModel()))
                }.onFailure {
                    Timber.e("채팅 상세 get 실패.")
                    _chatDetailUiState.emit(ChatDetailUiState.Failure(it.message))
                }
        }
    }
}