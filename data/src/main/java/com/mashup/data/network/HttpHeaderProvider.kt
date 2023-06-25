package com.mashup.data.network

interface HttpHeaderProvider {

    fun getToken(): String?
    fun saveToken(token: String?)
}