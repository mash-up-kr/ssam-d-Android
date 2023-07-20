package com.mashup.presentation.feature.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.mashup.domain.usecase.GetReceivedSignalUseCase
import com.mashup.domain.usecase.GetSubscribeKeywordsUseCase
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.feature.home.model.SignalUiModel.Companion.toUiModel
import com.mashup.presentation.feature.home.model.SubscribeKeywordUiModel
import com.mashup.presentation.feature.home.model.SubscribeKeywordUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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
    private val getReceivedSignalUseCase: GetReceivedSignalUseCase,
    private val getSubscribeKeywordsUseCase: GetSubscribeKeywordsUseCase
) : ViewModel() {

    var subscribeKeywords = mutableStateListOf<String>()

    private val _receivedSignals: MutableStateFlow<PagingData<SignalUiModel>> =
        MutableStateFlow(PagingData.empty())
    val receivedSignals = _receivedSignals.asStateFlow()

    private val _subscribeKeywordsState: MutableStateFlow<SubscribeKeywordUiState> =
        MutableStateFlow(Loading)
    val subscribeKeywordsState = _subscribeKeywordsState.asStateFlow()

    fun getSubscribedKeywords() {
        viewModelScope.launch {
            getSubscribeKeywordsUseCase.execute(Unit).catch {
                _subscribeKeywordsState.value = Error(it)
            }.collect {
                _subscribeKeywordsState.value = Success(it.toUiModel())
                subscribeKeywords.addAll(it.toUiModel().subscribeKeywords)
            }
        }
    }

    fun getReceivedSignal() {
        viewModelScope.launch {
            getReceivedSignalUseCase.execute(Unit).cachedIn(viewModelScope)
                .map { pagingData ->
                    pagingData.map { it.toUiModel() }
                }.collect {
                    _receivedSignals.value = it
                }
        }
    }


    fun addSubscribeKeywords(keyword: String) {
        subscribeKeywords.add(keyword)
    }

    fun deleteSubscribeKeywords(index: Int) {
        subscribeKeywords.removeAt(index)
    }


}

sealed interface SubscribeKeywordUiState
data class Success(val data: SubscribeKeywordUiModel) : SubscribeKeywordUiState
data class Error(val exception: Throwable) : SubscribeKeywordUiState
object Loading : SubscribeKeywordUiState
