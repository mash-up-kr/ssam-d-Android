package com.mashup.presentation.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.common.network.NetworkStatus
import com.mashup.presentation.common.network.NetworkStatusMonitor
import com.mashup.presentation.feature.error.NetworkErrorActivity
import com.mashup.presentation.feature.onboarding.OnBoardingActivity
import com.mashup.presentation.navigation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val networkStatusMonitor by lazy { NetworkStatusMonitor(this) }
    private val loginViewModel: LoginViewModel by viewModels()

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
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

        observeState()
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.checkScreenType()
    }

    private fun observeState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginUiState.collectLatest { uiState ->
                    when (uiState) {
                        LoginUiState.NICKNAME -> { loginViewModel.goToNicknamePage() }
                        LoginUiState.KEYWORD -> {
                            startActivity(Intent(this@LoginActivity, OnBoardingActivity::class.java))
                            finish()
                        }
                        LoginUiState.MAIN -> {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                networkStatusMonitor.networkStatus.collectLatest { networkStatus ->
                    when (networkStatus) {
                        is NetworkStatus.NetworkConnected -> {}
                        is NetworkStatus.NetworkDisconnected -> onNetworkDisconnected()
                    }
                }
            }
        }
    }
    private fun onNetworkDisconnected() {
        startActivity(Intent(this, NetworkErrorActivity::class.java))
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
                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오 계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                loginKakaoAccount()
            }
            token?.let { sendUserInfo() }
        }
    }

    private fun loginKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            error?.let {}
            token?.let { sendUserInfo() }
        }
    }

    private fun sendUserInfo() {
        UserApiClient.instance.me { user, error ->
            error?.let {}
            user?.let { loginViewModel.login(user.kakaoAccount?.email, user.id.toString()) }
        }
    }

    private fun handleOnBackPressed() {
        if (System.currentTimeMillis() - backPressedTime <= 500L) {
            finish()
        } else {
            Toast.makeText(this, getString(R.string.app_finish_toast), Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
