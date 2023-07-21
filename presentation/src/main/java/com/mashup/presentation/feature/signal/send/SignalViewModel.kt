package com.mashup.presentation.feature.signal

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetRecommendKeywordUseCase
import com.mashup.domain.usecase.SendSignalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import timber.log.Timber
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
@HiltViewModel
class SignalViewModel @Inject constructor(
    private val getRecommendKeywordUseCase: GetRecommendKeywordUseCase,
    private val sendSignalUseCase: SendSignalUseCase
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<KeywordUiState> =
        MutableStateFlow(KeywordUiState.Loading)
    val uiStateFlow: StateFlow<KeywordUiState> = _uiStateFlow.asStateFlow()

    private val _eventFlow: MutableSharedFlow<SignalUiEvent> = MutableSharedFlow<SignalUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val keywordListState = mutableStateListOf<String>()

    fun getRecommendKeywords(content: String) {
        viewModelScope.launch {
            delay(1000L)  // shimmer loading
            getRecommendKeywordUseCase.execute(content).catch {
                _uiStateFlow.value = KeywordUiState.Error(it)
            }.collect { keywords ->
                _uiStateFlow.value = KeywordUiState.Success
                keywordListState.apply {
                    keywords.forEach { add(it) }
                }
            }
        }
    }

    fun addKeyword(keyword: String) {
        keywordListState.add(keyword)
    }

    fun deleteKeyword(index: Int) {
        keywordListState.removeAt(index)
    }

    fun sendSignal(content: String) {
        val requestPair: Pair<String, List<String>> =
            Pair(content, keywordListState.toImmutableList())
        viewModelScope.launch {
            sendSignalUseCase.execute(param = requestPair)
                .onSuccess {
                    _eventFlow.emit(SignalUiEvent.SendSignalSuccess)
                }
                .onFailure {
                    _eventFlow.emit(SignalUiEvent.Error(it))
                }
        }
    }
}

sealed interface KeywordUiState {
    object Success : KeywordUiState
    data class Error(val throwable: Throwable) : KeywordUiState
    object Loading : KeywordUiState
}

sealed interface SignalUiEvent {
    object SendSignalSuccess : SignalUiEvent
    data class Error(val throwable: Throwable) : SignalUiEvent
    object Nothing : SignalUiEvent
}