package com.mashup.presentation.feature.signal

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetRecommendKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<KeywordUiState> =
        MutableStateFlow(KeywordUiState.Loading)
    val uiStateFlow: StateFlow<KeywordUiState> = _uiStateFlow.asStateFlow()

    val keywordListState = mutableStateListOf<String>()

    fun getRecommendKeywords(content: String) {
        viewModelScope.launch {
            delay(1000L)
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

    fun setSignalContent(content: String) {
        Log.e("TAG", "content : $content")
    }
}

sealed interface KeywordUiState {
    object Success : KeywordUiState
    data class Error(val throwable: Throwable) : KeywordUiState
    object Loading : KeywordUiState
}