package com.mashup.presentation.onboarding


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.SaveOnboardingKeywordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnboardingKeywordsUseCase: SaveOnboardingKeywordsUseCase
) : ViewModel() {
    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Editing(emptyList()))

    fun saveKeywords(keywords: List<String>) {
        viewModelScope.launch {
            uiState.emit(UiState.Loading)
            saveOnboardingKeywordsUseCase.execute(keywords)
                .onSuccess {
                    uiState.emit(UiState.SaveSuccess)
                }
                .onFailure {
                    it.message?.let {
                        uiState.emit(UiState.SaveFailed(it))
                    }
                }
        }
    }

    fun addKeyword(keyword: String) {
        if (uiState.value is UiState.Editing) {
            val keywords = (uiState.value as UiState.Editing).keywords.toMutableList()

            keywords.add(keyword)
            viewModelScope.launch {
                uiState.emit(UiState.Editing(keywords.toList()))
            }
        }
    }

    fun removeKeyword(index: Int) {
        if (uiState.value is UiState.Editing) {
            val keywords = (uiState.value as UiState.Editing).keywords.toMutableList()

            keywords.removeAt(index)
            viewModelScope.launch {
                uiState.emit(UiState.Editing(keywords.toList()))
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object SaveSuccess : UiState()
        data class SaveFailed(val message: String) : UiState()

        data class Editing(val keywords: List<String>) : UiState()
    }
}