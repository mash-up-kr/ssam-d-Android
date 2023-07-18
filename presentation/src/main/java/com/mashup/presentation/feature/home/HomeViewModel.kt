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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/07
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getReceivedSignalUseCase: GetReceivedSignalUseCase
) : ViewModel() {


    private val _pagingData: MutableStateFlow<PagingData<SignalUiModel>> =
        MutableStateFlow(PagingData.empty())
    val pagingData = _pagingData.asStateFlow()


    fun getReceivedSignal() {
        viewModelScope.launch {
            getReceivedSignalUseCase.execute(Unit).cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { SignalUiModel().toUiModel(it) }
                }.collect {
                    _pagingData.value = it
                }
        }
    }

}

sealed interface HomeUiState
data class Success(val data: HomeUiModel) : HomeUiState
data class Error(val exception: Throwable) : HomeUiState
object Loading : HomeUiState
