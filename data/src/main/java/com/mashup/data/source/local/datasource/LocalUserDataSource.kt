package com.mashup.data.source.local.datasource

import com.mashup.data.network.AppHeaderProvider
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(
    private val appHeaderProvider: AppHeaderProvider
) {

    fun saveToken(token: String?) {
        appHeaderProvider.saveToken(token)
    }
}