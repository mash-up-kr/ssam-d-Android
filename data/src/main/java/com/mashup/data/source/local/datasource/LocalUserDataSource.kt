package com.mashup.data.source.local.datasource

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(
    application: Application
) {
    private val preferences: SharedPreferences =
        application.getSharedPreferences(LOGIN_PREFERENCE, Context.MODE_PRIVATE)

    fun getToken(): String {
        return preferences.getString(JWT, "") ?: ""
    }

    fun saveToken(token: String?) {
        preferences.edit().putString(JWT, token).apply()
    }

    fun removeToken() {
        val token = getToken()
        preferences.edit().remove(token).apply ()
    }

    fun getUserId(): Long {
        return preferences.getLong(USER_ID, -1)
    }

    fun setUserId(id: Long) {
        preferences.edit().putLong(USER_ID, id).apply()
    }

    fun getIsLoginCompleted(): Boolean {
        return preferences.getBoolean(IS_LOGIN_COMPLETED, false)
    }

    fun setIsLoginCompleted(isLoginCompleted: Boolean) {
        preferences.edit().putBoolean(IS_LOGIN_COMPLETED, isLoginCompleted).apply()
    }

    companion object {
        private const val LOGIN_PREFERENCE = "LOGIN_PREFERENCE"

        private const val JWT = "JWT"
        private const val USER_ID = "USER_ID"
        private const val IS_LOGIN_COMPLETED = "IS_LOGIN_COMPLETED"
    }
}