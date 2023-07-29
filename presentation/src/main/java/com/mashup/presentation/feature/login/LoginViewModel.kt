package com.mashup.presentation.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.exception.ConflictException
import com.mashup.domain.usecase.login.*
import com.mashup.presentation.ui.common.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkNicknameDuplicationUseCase: CheckNicknameDuplicationUseCase,
    private val patchNicknameUseCase: PatchNicknameUseCase,
    private val getEntryScreenTypeUseCase: GetEntryScreenTypeUseCase
) : ViewModel() {

    init {
        checkScreenType()
    }

    var currentPage by mutableStateOf(0)
    var nickname by mutableStateOf("")

    private val _nicknameState: MutableStateFlow<ValidationState> =
        MutableStateFlow(ValidationState.EMPTY)
    val nicknameState = _nicknameState.asStateFlow()

    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.IDLE)
    val loginUiState = _loginUiState.asStateFlow()

    fun goToNextPage() = currentPage++

    fun backToPrevPage() = currentPage--

    private fun checkScreenType() {
        viewModelScope.launch {
            when (getEntryScreenTypeUseCase.execute(Unit)) {
                ScreenType.LOGIN -> _loginUiState.emit(LoginUiState.LOGIN)
                ScreenType.NICKNAME -> _loginUiState.emit(LoginUiState.NICKNAME)
                ScreenType.KEYWORD -> _loginUiState.emit(LoginUiState.KEYWORD)
                else -> _loginUiState.emit(LoginUiState.MAIN)
            }
        }
    }

    fun login(email: String?, socialId: String, deviceToken: String? = "") {
        viewModelScope.launch {
            val param = LoginParam(email = email, socialId = socialId, deviceToken = deviceToken)
            loginUseCase.execute(param)
                .onSuccess {
                    checkScreenType()
                }.onFailure {}
        }
    }

    fun getNicknameDuplication(nickname: String) {
        if (nickname.isBlank()) {
            _nicknameState.value = ValidationState.EMPTY
            return
        }
        viewModelScope.launch {
            checkNicknameDuplicationUseCase.execute(nickname)
                .onSuccess {
                    _nicknameState.value = ValidationState.SUCCESS
                }.onFailure {
                    when (it) {
                        is ConflictException -> {
                            _nicknameState.value = ValidationState.FAILED
                        }
                        else -> {}
                    }
                }
        }
    }

    fun patchNickname(nickname: String) {
        viewModelScope.launch {
            patchNicknameUseCase.execute(nickname)
                .onSuccess {
                    this@LoginViewModel.nickname = nickname
                    goToNextPage()
                }.onFailure {}
        }
    }
}

enum class LoginUiState {
    IDLE, LOGIN, NICKNAME, KEYWORD, MAIN
}
