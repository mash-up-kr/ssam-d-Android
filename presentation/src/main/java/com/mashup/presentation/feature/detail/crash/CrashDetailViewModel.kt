package com.mashup.presentation.feature.detail.crash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.crash.GetCrashDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrashDetailViewModel @Inject constructor(
    private val getCrashDetailUseCase: GetCrashDetailUseCase
) : ViewModel() {
    private val _crashUiState: MutableStateFlow<CrashDetailUiState> =
        MutableStateFlow<CrashDetailUiState>(CrashDetailUiState.Loading)
    val crashUiState = _crashUiState.asStateFlow()

    fun getCrashDetail(crashId: Long) {
        viewModelScope.launch {
            getCrashDetailUseCase.execute(params = crashId)
                .catch {
                    _crashUiState.value = CrashDetailUiState.Error(it.message)
                }.collect {
                    _crashUiState.value = CrashDetailUiState.Success(
                        crash = CrashUiModel.toUiModel(it)
                    )
                }
        }
    }
}

sealed interface CrashDetailUiState {
    object Loading : CrashDetailUiState
    data class Success(val crash: CrashUiModel) : CrashDetailUiState
    data class Error(val message: String?) : CrashDetailUiState
}
