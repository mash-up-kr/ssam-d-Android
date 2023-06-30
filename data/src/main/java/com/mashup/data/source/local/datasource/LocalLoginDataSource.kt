package com.mashup.data.source.local.datasource

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.mashup.data.network.AppHeaderProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    private val appHeaderProvider: AppHeaderProvider
) {

    fun saveToken(token: String?) {
        appHeaderProvider.saveToken(token)
    }
}