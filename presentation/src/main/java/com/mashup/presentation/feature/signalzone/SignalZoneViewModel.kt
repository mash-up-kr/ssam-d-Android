package com.mashup.presentation.feature.signalzone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mashup.domain.usecase.crash.GetCrashesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignalZoneViewModel @Inject constructor(
    private val getCrashes: GetCrashesUseCase
) : ViewModel() {
    private val _crashes: Flow<PagingData<SignalZoneUiModel>> =
        getCrashes.execute(Unit).cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { crash -> SignalZoneUiModel.toUiModel(crash) }
        }
    val crashes = _crashes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = PagingData.empty()
    )

    sealed class SignalZoneUiState {
        object Loading : SignalZoneUiState()
        data class Success(val crashes: PagingData<SignalZoneUiModel>) : SignalZoneUiState()
        data class Failure(val message: String) : SignalZoneUiState()
    }
}
