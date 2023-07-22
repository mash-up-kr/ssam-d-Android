package com.mashup.presentation.feature.signal.received

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetReceivedSignalDetailUseCase
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.signal.received.model.ReceivedSignalDetailUiModel
import com.mashup.presentation.feature.signal.received.model.ReceivedSignalDetailUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
@HiltViewModel
class ReceivedSignalViewModel @Inject constructor(
    private val getReceivedSignalDetailUseCase: GetReceivedSignalDetailUseCase
) : ViewModel() {

    private val _receivedSignalUiState: MutableStateFlow<ReceivedSignalDetailUiState> =
        MutableStateFlow(Loading)
    val receivedSignalUiState = _receivedSignalUiState.asStateFlow()

    fun getReceivedSignalDetail(signalId: Long) {
        viewModelScope.launch {
            getReceivedSignalDetailUseCase.execute(params = signalId)
                .catch {
                    _receivedSignalUiState.value = Error(it.message)
                }.collect {
                    _receivedSignalUiState.value = Success(
                        data = it.toUiModel()
                    )
                }
        }
    }
}

sealed interface ReceivedSignalDetailUiState
object Loading : ReceivedSignalDetailUiState
data class Success(val data: ReceivedSignalDetailUiModel) : ReceivedSignalDetailUiState
data class Error(val message: String?) : ReceivedSignalDetailUiState