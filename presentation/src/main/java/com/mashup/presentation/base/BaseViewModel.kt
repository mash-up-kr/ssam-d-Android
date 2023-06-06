package com.mashup.presentation.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    protected val context: Context
        get() = getApplication<Application>().applicationContext
}