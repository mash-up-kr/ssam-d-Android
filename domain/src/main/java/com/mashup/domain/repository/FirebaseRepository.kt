package com.mashup.domain.repository

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/31
 */
interface FirebaseRepository {

    suspend fun getFCMDeviceToken(): String
}