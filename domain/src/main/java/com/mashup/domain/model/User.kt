package com.mashup.domain.model

import com.mashup.domain.base.DomainModel

data class User(
    val id: Long,
    val nickname: String,
    val email: String?,
    val provider: String,
    val profileImageUrl: String,
    val agreeAlarm: Boolean
): DomainModel