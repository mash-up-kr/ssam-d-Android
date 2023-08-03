package com.mashup.presentation.feature.profile.withdrawal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/03
 */
@HiltViewModel
class WithdrawalViewModel @Inject constructor(

) : ViewModel() {
    private val _eventFlow: MutableSharedFlow<WithdrawalUiEvent> =
        MutableSharedFlow<WithdrawalUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun withdrawal() {
        viewModelScope.launch {
            _eventFlow.emit(Withdrawal)
        }
    }
}

sealed interface WithdrawalUiEvent
object Idle : WithdrawalUiEvent
object Withdrawal : WithdrawalUiEvent
data class Failure(val message: String?) : WithdrawalUiEvent
