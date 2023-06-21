package com.mashup.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mashup.presentation.common.extension.setThemeContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setThemeContent {
            LoginScreen(navigateToOnBoarding = {})
        }
    }
}
