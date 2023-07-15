package com.mashup.presentation.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val optionsProvider: ProfileOptionsProvider
) : ViewModel() {

    private val _optionsList: MutableStateFlow<List<ProfileViewType>> = MutableStateFlow(emptyList())
    val optionsList: StateFlow<List<ProfileViewType>> = _optionsList.asStateFlow()

    init {
        viewModelScope.launch {
            _optionsList.value = optionsProvider.getOptions()
        }
    }

    fun toggleNotificationSwitch(isChecked: Boolean) {
        viewModelScope.launch {

        }
    }
}