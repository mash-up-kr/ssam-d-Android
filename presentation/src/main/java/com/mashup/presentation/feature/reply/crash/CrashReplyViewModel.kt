package com.mashup.presentation.feature.reply.crash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.exception.KeyLinkException
import com.mashup.domain.usecase.crash.ReceivedCrashReplyParams
import com.mashup.domain.usecase.crash.SendCrashReplyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrashReplyViewModel @Inject constructor(
    private val sendCrashReplyUseCase: SendCrashReplyUseCase
) : ViewModel() {

    private val _eventFlow: MutableSharedFlow<CrashReplyUiEvent> =
        MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    fun sendCrashReply(crashId: Long, content: String) {
        viewModelScope.launch {
            val replyRequestParams = ReceivedCrashReplyParams(
                crashId = crashId,
                content = content
            )

            sendCrashReplyUseCase.execute(param = replyRequestParams)
                .onSuccess {
                    _eventFlow.emit(CrashReplyUiEvent.SendReplySuccess)
                }
                .onFailure { exception ->
                    when (exception) {
                        is KeyLinkException -> {
                            _eventFlow.emit(CrashReplyUiEvent.SendReplyFailed(exception.message))
                        }
                    }
                }
        }
    }
}

sealed interface CrashReplyUiEvent {
    object Idle : CrashReplyUiEvent
    object SendReplySuccess : CrashReplyUiEvent
    data class SendReplyFailed(val message: String?) : CrashReplyUiEvent
}