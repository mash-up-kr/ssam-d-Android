package com.mashup.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.chat.*
import androidx.paging.map
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.chat.compose.MessageReplyUiState
import com.mashup.presentation.feature.detail.chat.model.toUiModel
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import com.mashup.presentation.feature.detail.chat.compose.ChatInfoUiState
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel.Companion.toUiModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel.Companion.toUiModel
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

    private val _chatInfoUiState: MutableStateFlow<ChatInfoUiState> = MutableStateFlow(ChatInfoUiState.Loading)
    val chatDetailUiState = _chatInfoUiState.asStateFlow()

    private val _chatPagingData: MutableStateFlow<PagingData<ChatUiModel>> = MutableStateFlow(PagingData.empty())
    val chatPagingData = _chatPagingData.asStateFlow()

    private val _messageDetailUiState: MutableStateFlow<MessageDetailUiState> =
        MutableStateFlow(MessageDetailUiState.Loading)
    val messageDetailUiState = _messageDetailUiState.asStateFlow()

    private val _replyUiState: MutableStateFlow<MessageReplyUiState> =
        MutableStateFlow(MessageReplyUiState.Idle)
    val replyUiState = _replyUiState.asStateFlow()

    fun getChatInfo(id: Long) {
        viewModelScope.launch {
            getChatInfoUseCase.execute(id)
                .catch {
                    Timber.e("채팅방 정보 get 실패.")
                    _chatInfoUiState.value = ChatInfoUiState.Failure(it.message)
                }.collect {
                    Timber.i("채팅방 정보 get 성공~!")
                    _chatInfoUiState.value = ChatInfoUiState.Success(it.toUiModel())
                }
        }
    }

    fun getChats(id: Long) {
        viewModelScope.launch {
            getChatsUseCase.execute(id).cachedIn(viewModelScope).map { pagingData ->
                pagingData.map { chat -> chat.toUiModel() }
            }.collect {
                Timber.i("채팅방 채팅 목록 get 성공~!")
                _chatPagingData.value = it
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
}
