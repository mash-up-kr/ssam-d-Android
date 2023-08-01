package com.mashup.domain.usecase

import com.mashup.domain.repository.FirebaseRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/02
 */
class GetFCMDeviceTokenUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : CoroutineUseCase<Unit, String>() {
    override suspend fun invoke(param: Unit): String {
        return firebaseRepository.getFCMDeviceToken()
    }
}