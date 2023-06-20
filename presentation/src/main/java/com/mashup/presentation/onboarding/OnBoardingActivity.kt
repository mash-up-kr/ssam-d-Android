package com.mashup.presentation.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mashup.presentation.common.extension.setThemeContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemeContent {
            OnBoardingScreen()
        }
    }
}