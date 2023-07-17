package com.mashup.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mashup.domain.usecase.GetReceivedSignalUseCase
import com.mashup.presentation.feature.home.model.HomeUiModel
import com.mashup.presentation.feature.home.model.SignalUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/07
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    getReceivedSignalUseCase: GetReceivedSignalUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(Loading)
    val uiState = _uiState.asStateFlow()

    val pagingData = getReceivedSignalUseCase.execute(Unit)
        .cachedIn(viewModelScope)
        .map { pagingData ->
            pagingData.map { SignalUiModel().toUiModel(it) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty()
        )
}

sealed interface HomeUiState
data class Success(val data: HomeUiModel) : HomeUiState
data class Error(val exception: Throwable) : HomeUiState
object Loading : HomeUiState
