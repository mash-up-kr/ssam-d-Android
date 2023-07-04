package com.mashup.presentation.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.LoginParam
import com.mashup.domain.usecase.LoginUseCase
import com.mashup.domain.usecase.PatchNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val patchNicknameUseCase: PatchNicknameUseCase
): ViewModel() {

    var currentPage by mutableStateOf(0)
    var nickname by mutableStateOf("")

    private fun goToNextPage() = currentPage++

    fun backToPrevPage() = currentPage--

    fun login(email: String?, socialId: String, deviceToken: String? = "") {
        viewModelScope.launch {
            val param = LoginParam(email = email, socialId = socialId, deviceToken = deviceToken)
            loginUseCase.execute(param)
               .onSuccess { result ->
                   if (result) {
                       Timber.i("드디어 로그인 성공~!")
                       goToNextPage()
                   }
               }.onFailure {
                    Timber.i("삐빅- 로그인 실패")
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
