package com.mashup.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mashup.presentation.ui.setThemeContent
import com.mashup.presentation.ui.theme.SsamDTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemeContent {
            LoginScreen (
                onLoginButtonClicked = loginViewModel::handleKakaoLogin
            )
        }
    }
}