package com.mashup.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mashup.presentation.common.extension.setThemeContent
import androidx.core.view.WindowCompat
import com.mashup.presentation.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setThemeContent {
            LoginScreen(loginToOnBoarding = {
                startActivity(Intent(this, OnBoardingActivity::class.java))
            })
        }
    }
}
