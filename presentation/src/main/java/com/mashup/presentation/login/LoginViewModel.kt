package com.mashup.presentation.login

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val context: Context
        get() = getApplication<Application>().applicationContext

    var currentPage by mutableStateOf(0)
    var nickname by mutableStateOf("")

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
            if (error != null) {
                Timber.e("카카오톡으로 로그인 실패그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오 계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                loginKakaoAccount()
            } else if (token != null) {
                Timber.i("카카오톡으로 로그인 성공 ${token.accessToken}")
                addPage()
            }
        }
    }

    private fun loginKakaoAccount() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.e("카카오 계정으로 로그인 실패", error)
            } else if (token != null) {
                Timber.i("카카오 계정으로 로그인 성공 ${token.accessToken}")
                addPage()
            }
        }

        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }

    fun setNicknameAndAddPage(nickname: String) {
        this.nickname = nickname
        addPage()
    }

    private fun addPage() = currentPage++

    fun onBackPressed() {
        if (currentPage > 0) {
            currentPage--
        } else {
            // 두 번 클릭 시 종료되게?
            // or 다이얼로그 띄우기?
        }
    }
}
