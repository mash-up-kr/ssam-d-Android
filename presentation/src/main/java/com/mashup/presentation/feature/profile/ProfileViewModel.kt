package com.mashup.presentation.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.mypage.GetUserInformationUseCase
import com.mashup.domain.usecase.mypage.SaveAlarmStateUseCase
import com.mashup.presentation.feature.profile.model.ProfileUiModel
import com.mashup.domain.usecase.mypage.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val optionsProvider: ProfileOptionsProvider,
    private val getUserInformation: GetUserInformationUseCase,
    private val saveAlarmState: SaveAlarmStateUseCase,
    private val logout: LogoutUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _eventFlow: MutableSharedFlow<LogoutUiEvent> = MutableSharedFlow<LogoutUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        initProfile()
    }

    private fun initProfile() {
        viewModelScope.launch {
            getUserInformation.execute(Unit)
                .onSuccess {
                    val profileList = optionsProvider.getOptions(ProfileUiModel.fromDomainModel(it))
                    _uiState.emit(
                        UiState.Success(profileList = profileList)
                    )
                }
                .onFailure {
                    _uiState.emit(UiState.Failure(it))
                }
        }
    }

    fun toggleNotificationSwitch(isChecked: Boolean) {
        viewModelScope.launch {
            saveAlarmState.execute(isChecked)
        }
    }

    fun logout() {
        viewModelScope.launch {
            logout.execute(Unit)
                .onSuccess {
                    _eventFlow.emit(Logout)
                }
                .onFailure {
                    _eventFlow.emit(LogoutFailed(it.message))
                }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(
            val profileList: List<ProfileViewType>
        ) : UiState()

        data class Failure(
            val throwable: Throwable
        ) : UiState()
    }
}

sealed interface LogoutUiEvent
object Idle : LogoutUiEvent
object Logout : LogoutUiEvent
data class LogoutFailed(val message: String?) : LogoutUiEvent