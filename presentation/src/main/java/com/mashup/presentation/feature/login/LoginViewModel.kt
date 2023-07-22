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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkNicknameDuplicationUseCase: CheckNicknameDuplicationUseCase,
    private val patchNicknameUseCase: PatchNicknameUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : ViewModel() {

    init {
        checkAutoLogin()
    }

    var currentPage by mutableStateOf(0)
    var nickname by mutableStateOf("")

    private val _nicknameState: MutableStateFlow<ValidationState> =
        MutableStateFlow(ValidationState.EMPTY)
    val nicknameState = _nicknameState.asStateFlow()

    private val _loginUiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.IDLE)
    val loginUiState = _loginUiState.asStateFlow()

    private fun goToNextPage() = currentPage++

    fun backToPrevPage() = currentPage--

    private fun checkAutoLogin() {
        viewModelScope.launch {
            if (getAccessTokenUseCase.execute(Unit).isNotBlank()) {
                _loginUiState.emit(LoginUiState.LOGIN)
            }
        }
    }

    fun login(email: String?, socialId: String, deviceToken: String? = "") {
        viewModelScope.launch {
            val param = LoginParam(email = email, socialId = socialId, deviceToken = deviceToken)
            loginUseCase.execute(param)
                .onSuccess {
                    Timber.i("드디어 로그인 성공~!")
                    goToNextPage()
                }.onFailure {
                    Timber.i("삐빅- 로그인 실패")
                }
        }
    }

    fun getNicknameDuplication(nickname: String) {
        if (nickname.isEmpty()) {
            _nicknameState.value = ValidationState.EMPTY
            return
        }
        viewModelScope.launch {
            checkNicknameDuplicationUseCase.execute(nickname)
                .onSuccess {
                    Timber.i("중복된 닉네임 없음ㅋ")
                    _nicknameState.value = ValidationState.SUCCESS
                }.onFailure {
                    when (it) {
                        is ConflictException -> {
                            Timber.e("중복된 닉네임당")
                            _nicknameState.value = ValidationState.FAILED
                        }
                        else -> Timber.e("삐빅- 에러 발생 WHY? " + it.message)
                    }
                }
        }
    }

    fun patchNickname(nickname: String) {
        viewModelScope.launch {
            patchNicknameUseCase.execute(nickname)
                .onSuccess {
                    Timber.i("닉네임 세팅 성공~!")
                    this@LoginViewModel.nickname = nickname
                    goToNextPage()
                }.onFailure {
                    Timber.e("삐빅- 닉네임 세팅 실패 WHY? " + it.message)
                }
        }
    }
}

enum class LoginUiState {
    IDLE, LOGIN, NICKNAME, KEYWORDS, COMPLETED
}
