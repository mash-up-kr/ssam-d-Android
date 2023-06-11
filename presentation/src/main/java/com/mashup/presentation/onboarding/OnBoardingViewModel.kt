package com.mashup.presentation.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OnBoardingViewModel: ViewModel() {
    var currentPage by mutableStateOf(0)
}