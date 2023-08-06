package com.mashup.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.mashup.domain.repository.FirebaseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/31
 */
class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getFCMDeviceToken(): String =
        suspendCancellableCoroutine { cancellableContinuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    task.exception?.run {
                        cancellableContinuation.cancel(this)
                    }
                }
                val fcmDeviceToken = task.result
                cancellableContinuation.resume(fcmDeviceToken) {}
            }
        }
}