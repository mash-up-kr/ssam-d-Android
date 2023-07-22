package com.mashup.data.source.local.datasource

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
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
        preferences.edit().putString(JWT, "").apply ()
    }

    fun getUserId(): Long {
        return preferences.getLong(USER_ID, -1)
    }

    fun setUserId(id: Long) {
        preferences.edit().putLong(USER_ID, id).apply()
    }

    fun getNickname(): String {
        return preferences.getString(NICKNAME, "") ?: ""
    }

    fun setNickname(nickname: String?) {
        preferences.edit().putString(NICKNAME, nickname).apply()
    }

    fun getKeywords(): List<String> {
        val jsonArr = JSONArray(preferences.getString(KEYWOREDS, "") ?: "")
        val keywordList: MutableList<String> = mutableListOf()
        for (i in 0 until jsonArr.length()) {
            keywordList.add(jsonArr.optString(i))
        }
        return keywordList.toList()
    }

    fun setKeywords(keywords: List<String>) {
        val jsonArr = JSONArray()
        for (keyword in keywords) {
            jsonArr.put(keyword)
        }
        preferences.edit().putString(KEYWOREDS, jsonArr.toString()).apply()
    }

    companion object {
        private const val LOGIN_PREFERENCE = "LOGIN_PREFERENCE"

        private const val JWT = "JWT"
        private const val USER_ID = "USER_ID"
        private const val NICKNAME = "NICKNAME"
        private const val KEYWOREDS = "KEYWOREDS"
    }
}