package com.mashup.presentation.feature.signal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetRecommendKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
    private val _keywordsState: MutableStateFlow<KeywordUiState> = MutableStateFlow(KeywordUiState.Loading)
    val keywordsState: StateFlow<KeywordUiState> = _keywordsState.asStateFlow()

    fun getRecommendKeywords() {
        viewModelScope.launch {
            getRecommendKeywordUseCase.execute("안녕하세요 우히히").catch {
//                Log.e("TAG","$it")
                _keywordsState.value = KeywordUiState.Error(it)
            }.collect {
//                Log.e("TAG","Success")
//                Log.e("TAG", "$it")
                _keywordsState.value = KeywordUiState.Success(it)
            }
        }
    }

    fun setSignalContent(content: String) {
        Log.e("TAG", "content : $content")
    }
}

sealed interface KeywordUiState {
    data class Success(val keywords: List<String> = emptyList()) : KeywordUiState {
        fun isEmpty(): Boolean = keywords.isEmpty()
    }
    data class Error(val throwable: Throwable) : KeywordUiState
    object Loading : KeywordUiState
}