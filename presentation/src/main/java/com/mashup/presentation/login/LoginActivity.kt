package com.mashup.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.mashup.presentation.common.extension.setThemeContent
import androidx.core.view.WindowCompat
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mashup.presentation.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setThemeContent {
            LoginRoute(
                loginButtonClicked = { handleKakaoLogin() },
                loginToOnBoarding = {
                    startActivity(Intent(this, OnBoardingActivity::class.java))
                    finish()
                },
                handleOnBackPressed = { handleOnBackPressed() }
            )
        }
    }

    private fun handleKakaoLogin() {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오 계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            loginKakaoTalk()
        } else {
            loginKakaoAccount()
        }
    }

    private fun loginKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
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
                Timber.i("카카오톡 로그인 성공 token: ${token.accessToken}")
                sendUserInfo()
            }
        }
    }

    private fun loginKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            error?.let {
                Timber.e("카카오 계정 로그인 실패", error)
            }
            token?.let {
                Timber.i("카카오 계정 로그인 성공 token: ${token.accessToken}")
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
                loginViewModel.login(user.kakaoAccount?.email, user.id.toString())
            }
        }
    }

    private fun handleOnBackPressed() {
        if (System.currentTimeMillis() - backPressedTime <= 500L) {
            finish()
        } else {
            Toast.makeText(this, "한 번 더 클릭하면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
