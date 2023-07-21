package com.mashup.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.chat.*
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailUiState
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.chat.compose.MessageReplyUiState
import com.mashup.presentation.feature.detail.chat.model.toUiModel
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val getChatInfoUseCase: GetChatInfoUseCase,
    private val getChatsUseCase: GetChatsUseCase,
    private val disconnectRoomUseCase: DisconnectRoomUseCase,
    private val getMessageDetailUseCase: GetMessageDetailUseCase,
    private val replyUseCase: ReplyUseCase
) : ViewModel() {

    private val _chatDetailUiState: MutableStateFlow<ChatDetailUiState> = MutableStateFlow(
        ChatDetailUiState.Loading
    )
    val chatDetailUiState = _chatDetailUiState.asStateFlow()

    private val _messageDetailUiState: MutableStateFlow<MessageDetailUiState> =
        MutableStateFlow(MessageDetailUiState.Loading)
    val messageDetailUiState = _messageDetailUiState.asStateFlow()

    private val _replyUiState: MutableStateFlow<MessageReplyUiState> =
        MutableStateFlow(MessageReplyUiState.Idle)
    val replyUiState = _replyUiState.asStateFlow()

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

    fun getMessageDetail(roomId: Long, chatId: Long) {
        viewModelScope.launch {
            val param = GetMessageDetailUseCase.MessageDetailParam(
                roomId = roomId,
                chatId = chatId
            )
            getMessageDetailUseCase.execute(param).collectLatest {
                _messageDetailUiState.emit(
                    MessageDetailUiState.Success(
                        messageDetail = MessageDetailUiModel.fromDomainModel(it)
                    )
                )
            }
        }
    }

    fun reply(roomId: Long, content: String) {
        val param = ReplyUseCase.Param(
            roomId = roomId,
            content = content
        )
        viewModelScope.launch {
            _replyUiState.emit(MessageReplyUiState.Loading)

            replyUseCase.execute(param)
                .onSuccess {
                    _replyUiState.emit(MessageReplyUiState.SaveSuccess)
                }
                .onFailure {
                    _replyUiState.emit(MessageReplyUiState.Failure(it.message))
                }
        }
    }

    companion object {
        const val PAGE_LENGTH = 10
    }
}