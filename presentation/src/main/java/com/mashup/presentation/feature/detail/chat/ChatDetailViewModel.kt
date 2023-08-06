package com.mashup.presentation.feature.detail.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.chat.*
import androidx.paging.map
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mashup.domain.exception.KeyLinkException
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.chat.compose.MessageReplyUiEvent
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import com.mashup.presentation.feature.detail.chat.compose.ChatInfoUiState
import com.mashup.presentation.feature.detail.chat.compose.DisconnectRoomUiEvent
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel.Companion.toUiModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private val getChatInfoUseCase: GetChatInfoUseCase,
    private val disconnectRoomUseCase: DisconnectRoomUseCase,
    private val getMessageDetailUseCase: GetMessageDetailUseCase,
    private val replyUseCase: ReplyUseCase,
    getChatsUseCase: GetChatsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val roomId = savedStateHandle.get<Long>("roomId") ?: -1

    private val _chatInfoUiState: MutableStateFlow<ChatInfoUiState> =
        MutableStateFlow(ChatInfoUiState.Loading)
    val chatInfoUiState = _chatInfoUiState.asStateFlow()

    private val _chatPagingData: Flow<PagingData<ChatUiModel>> =
        getChatsUseCase.execute(roomId).cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { chat -> chat.toUiModel() }
        }

    val chatPagingData = _chatPagingData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = PagingData.empty()
    )

    private val _messageDetailUiState: MutableStateFlow<MessageDetailUiState> =
        MutableStateFlow(MessageDetailUiState.Loading)
    val messageDetailUiState = _messageDetailUiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<MessageReplyUiEvent> = MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _showSnackbarMessage: MutableSharedFlow<DisconnectRoomUiEvent> = MutableSharedFlow()
    val showSnackbarMessage = _showSnackbarMessage.asSharedFlow()

    fun getChatInfo() {
        viewModelScope.launch {
            getChatInfoUseCase.execute(roomId)
                .catch {
                    _chatInfoUiState.value = ChatInfoUiState.Failure(it.message)
                }.collect {
                    _chatInfoUiState.value = ChatInfoUiState.Success(it.toUiModel())
                }
        }
    }

    fun disconnectRoom() {
        viewModelScope.launch {
            disconnectRoomUseCase.execute(roomId)
                .onSuccess {
                    _showSnackbarMessage.emit(DisconnectRoomUiEvent.Disconnect)
                }
                .onFailure { exception ->
                    when (exception) {
                        is KeyLinkException -> {
                            _showSnackbarMessage.emit(
                                DisconnectRoomUiEvent.Failure(exception.message.orEmpty())
                            )
                        }
                    }
                }
        }
    }

    fun getMessageDetail(chatId: Long) {
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

    fun reply(content: String) {
        val param = ReplyUseCase.Param(
            roomId = roomId,
            content = content
        )
        viewModelScope.launch {
            replyUseCase.execute(param)
                .onSuccess {
                    _eventFlow.emit(MessageReplyUiEvent.SaveSuccess)
                }
                .onFailure {
                    _eventFlow.emit(MessageReplyUiEvent.Failure(it.message))
                }
        }
    }
}
