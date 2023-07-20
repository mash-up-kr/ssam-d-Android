package com.mashup.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.chat.DisconnectRoomUseCase
import com.mashup.domain.usecase.chat.GetChatInfoUseCase
import com.mashup.domain.usecase.chat.GetChatsParam
import com.mashup.domain.usecase.chat.GetChatsUseCase
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailUiState
import com.mashup.presentation.feature.detail.chat.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val getChatInfoUseCase: GetChatInfoUseCase,
    private val getChatsUseCase: GetChatsUseCase,
    private val disconnectRoomUseCase: DisconnectRoomUseCase
): ViewModel() {

    private val _chatDetailUiState: MutableStateFlow<ChatDetailUiState> = MutableStateFlow(
        ChatDetailUiState.Loading)
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

    fun disconnectRoom(roomId: Long) {
        viewModelScope.launch {
            disconnectRoomUseCase.execute(roomId)
        }
    }

    companion object {
        const val PAGE_LENGTH = 10
    }
}