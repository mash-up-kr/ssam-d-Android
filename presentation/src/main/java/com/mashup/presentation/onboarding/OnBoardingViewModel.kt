package com.mashup.presentation.onboarding


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.SaveOnboardingKeywordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnboardingKeywordsUseCase: SaveOnboardingKeywordsUseCase
): ViewModel() {
    val uiEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    var keywords = mutableStateListOf<String>()
        private set


    suspend fun saveKeywords(keywords:List<String>) {
        viewModelScope.launch {
            saveOnboardingKeywordsUseCase.execute(keywords)
                .onSuccess {
                    uiEvent.emit(UiEvent.SuccessSave)
                }
                .onFailure {
                    it.message?.let {
                        uiEvent.emit(UiEvent.ShowError(it))
                    }
                }
        }
    }

    fun addKeyword(keyword: String) {
        keywords.add(keyword)
    }

    fun removeKeyword(index: Int) {
        keywords.removeAt(index)
    }

    sealed class UiEvent {
        object SuccessSave: UiEvent()
        class ShowError(val message: String): UiEvent()
    }
}