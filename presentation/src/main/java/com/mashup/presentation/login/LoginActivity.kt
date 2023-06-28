package com.mashup.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mashup.presentation.common.extension.setThemeContent
import androidx.core.view.WindowCompat
import com.mashup.presentation.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setThemeContent {
            LoginScreen(
                loginToOnBoarding = {
                    startActivity(Intent(this, OnBoardingActivity::class.java))
                    finish()
                },
                handleOnBackPressed = { handleOnBackPressed() }
            )
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
