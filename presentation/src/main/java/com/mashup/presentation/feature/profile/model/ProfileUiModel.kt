package com.mashup.presentation.feature.profile.model

import com.mashup.domain.model.User

data class ProfileUiModel(
    val id: Long,
    val nickname: String,
    val email: String,
    val provider: String,
    val profileImageUrl: String,
    val agreeAlarm: Boolean
) {
    companion object {
        fun fromDomainModel(user: User): ProfileUiModel {
            with(user) {
                return ProfileUiModel(
                    id = id,
                    nickname = nickname,
                    email = email,
                    provider = provider,
                    profileImageUrl = profileImageUrl,
                    agreeAlarm = agreeAlarm
                )
            }
        }
    }
}
