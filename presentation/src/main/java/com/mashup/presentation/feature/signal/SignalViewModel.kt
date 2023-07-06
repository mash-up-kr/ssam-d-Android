package com.mashup.presentation.feature.signal

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetRecommendKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
@HiltViewModel
class SignalViewModel @Inject constructor(
    private val getRecommendKeywordUseCase: GetRecommendKeywordUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val signalContent = savedStateHandle.getStateFlow(SIGNAL_CONTENT, "")

    private val _keywordsState: MutableStateFlow<KeywordUiState> = MutableStateFlow(KeywordUiState.Loading)
    val keywordsState: StateFlow<KeywordUiState> = _keywordsState.asStateFlow()


    fun getRecommendKeywords(content: String) {
        viewModelScope.launch {
            getRecommendKeywordUseCase.execute(content).catch {
                _keywordsState.value = KeywordUiState.Error(it)
            }.collect {
                _keywordsState.value = KeywordUiState.Success(it)
            }
        }
    }

    fun setSignalContent(content: String) {
        savedStateHandle[SIGNAL_CONTENT] = content
    }
}

const val SIGNAL_CONTENT = "signal_content"

sealed interface KeywordUiState {
    data class Success(val keywords: List<String> = emptyList()) : KeywordUiState {
        fun isEmpty(): Boolean = keywords.isEmpty()
    }
    data class Error(val throwable: Throwable) : KeywordUiState
    object Loading : KeywordUiState
}