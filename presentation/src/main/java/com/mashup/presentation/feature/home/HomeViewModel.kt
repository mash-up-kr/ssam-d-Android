package com.mashup.presentation.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mashup.domain.usecase.GetReceivedSignalUseCase
import com.mashup.domain.usecase.GetSubscribeKeywordsUseCase
import com.mashup.domain.usecase.UpdateSubscribeKeywordsUseCase
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.feature.home.model.SignalUiModel.Companion.toUiModel
import com.mashup.presentation.feature.home.model.SubscribeKeywordUiModel
import com.mashup.presentation.feature.home.model.SubscribeKeywordUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/07
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getReceivedSignalUseCase: GetReceivedSignalUseCase,
    private val getSubscribeKeywordsUseCase: GetSubscribeKeywordsUseCase,
    private val updateSubscribeKeywordsUseCase: UpdateSubscribeKeywordsUseCase
) : ViewModel() {

    var subscribeKeywords = mutableStateListOf<String>()

    private val _receivedSignals: Flow<PagingData<SignalUiModel>> =
        getReceivedSignalUseCase.execute(Unit).cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { it.toUiModel() }
            }

    val receivedSignals = _receivedSignals.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = PagingData.empty()
    )

    private val _subscribeKeywordsState: MutableStateFlow<SubscribeKeywordUiState> =
        MutableStateFlow(Loading)
    val subscribeKeywordsState = _subscribeKeywordsState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<SubscribeKeywordUiEvent> =
        MutableSharedFlow()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getSubscribedKeywords() {
        viewModelScope.launch {
            getSubscribeKeywordsUseCase.execute(Unit).catch {
                _subscribeKeywordsState.value = Error(it)
            }.collect {
                _subscribeKeywordsState.value = Success(it.toUiModel())
            }
        }
    }

    fun updateSubscribeKeywords() {
        viewModelScope.launch {
            updateSubscribeKeywordsUseCase.execute(subscribeKeywords.toImmutableList())
                .onSuccess {
                    _eventFlow.emit(UpdateSuccess)
                }
                .onFailure {
                    _eventFlow.emit(UpdateFailed(it))
                }
        }
    }

    fun addSubscribeKeywords(keyword: String) {
        subscribeKeywords.add(keyword)
    }

    fun deleteSubscribeKeywords(index: Int) {
        subscribeKeywords.removeAt(index)
    }

    fun setSubscribeKeywords(keywords: List<String>) {
        subscribeKeywords = keywords.toMutableStateList()
    }
}

sealed interface SubscribeKeywordUiState
data class Success(val data: SubscribeKeywordUiModel) : SubscribeKeywordUiState
data class Error(val exception: Throwable) : SubscribeKeywordUiState
object Loading : SubscribeKeywordUiState

sealed interface SubscribeKeywordUiEvent
object UpdateSuccess : SubscribeKeywordUiEvent
data class UpdateFailed(val exception: Throwable) : SubscribeKeywordUiEvent
object Nothing : SubscribeKeywordUiEvent
