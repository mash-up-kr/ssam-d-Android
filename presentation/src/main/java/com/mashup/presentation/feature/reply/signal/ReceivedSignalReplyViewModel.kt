package com.mashup.presentation.feature.reply.signal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.ReceivedSignalReplyParams
import com.mashup.domain.usecase.SendReceivedSignalReplyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
@HiltViewModel
class ReceivedSignalReplyViewModel @Inject constructor(
    private val sendReceivedSignalReplyUseCase: SendReceivedSignalReplyUseCase
) : ViewModel() {

    private val _eventFlow: MutableSharedFlow<ReceivedSignalReplyUiEvent> =
        MutableSharedFlow<ReceivedSignalReplyUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun sendReceivedSignalReply(signalId: Long, content: String) {
        viewModelScope.launch {
            val replyRequestParams = ReceivedSignalReplyParams(
                signalId = signalId,
                content = content
            )

            sendReceivedSignalReplyUseCase.execute(param = replyRequestParams)
                .onSuccess {
                    _eventFlow.emit(SendReplySuccess)
                }
                .onFailure {
                    _eventFlow.emit(SendReplyFailed(it.message))
                }
        }
    }

}

sealed interface ReceivedSignalReplyUiEvent
object Idle : ReceivedSignalReplyUiEvent
object SendReplySuccess : ReceivedSignalReplyUiEvent
data class SendReplyFailed(val message: String?) : ReceivedSignalReplyUiEvent