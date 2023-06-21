package com.mashup.data.source.local.datasource

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    application: Application
) {
    private val preferences: SharedPreferences = application.getSharedPreferences(LOGIN_PREFERENCE, Context.MODE_PRIVATE)

    fun getJwt() = preferences.getString(JWT, "") ?: ""

    fun saveAccessToken() {
        // TODO: 구현
    }

    companion object {
        private const val LOGIN_PREFERENCE = "LOGIN_PREFERENCE"
        private const val JWT = "JWT"
    }
}