package com.mashup.presentation.login

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
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
    private val patchNicknameUseCase: PatchNicknameUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val context: Context
        get() = getApplication<Application>().applicationContext

    var currentPage by mutableStateOf(0)
    var nickname by mutableStateOf("")

    private fun goToNextPage() = currentPage++

    fun backToPrevPage() = currentPage--

    fun handleKakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            loginKakaoTalk()
        } else {
            loginKakaoAccount()
        }
    }

    private fun loginKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            error?.let {
                Timber.e("카카오톡 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오 계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                loginKakaoAccount()
            }
            token?.let {
                Timber.i("카카오톡 로그인 성공 ${token.accessToken}")
                sendUserInfo()
            }
        }
    }

    private fun loginKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            error?.let {
                Timber.e("카카오 계정 로그인 실패", error)
            }
            token?.let {
                Timber.i("카카오 계정 로그인 성공 ${token.accessToken}")
                sendUserInfo()
            }
        }
    }

    private fun sendUserInfo() {
        UserApiClient.instance.me { user, error ->
            error?.let {
                Timber.e("사용자 정보 요청 실패", error)
            }
            user?.let {
                login(user.kakaoAccount?.email, user.id.toString())
            }
        }
    }

    private fun login(email: String?, socialId: String, deviceToken: String? = "") {
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
