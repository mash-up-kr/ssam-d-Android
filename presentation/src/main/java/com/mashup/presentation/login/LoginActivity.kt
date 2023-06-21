package com.mashup.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mashup.presentation.common.extension.setThemeContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemeContent {
            LoginScreen(navigateToOnBoarding = ::navigateToOnBoarding)
        }
    }

    private fun navigateToOnBoarding() {
        // 임시 생성
    }
}