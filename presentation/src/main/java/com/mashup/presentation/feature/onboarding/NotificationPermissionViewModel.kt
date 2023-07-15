package com.mashup.presentation.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.mypage.SaveAlarmStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationPermissionViewModel @Inject constructor(
    private val saveAlarmState: SaveAlarmStateUseCase
) : ViewModel() {
    fun toggleNotificationSwitch(isChecked: Boolean) {
        viewModelScope.launch {
            saveAlarmState.execute(isChecked)
        }
    }
}