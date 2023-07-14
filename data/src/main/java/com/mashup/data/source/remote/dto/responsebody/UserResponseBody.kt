package com.mashup.data.source.remote.dto.responsebody

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponseBody (
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "nickname")
    val nickname: String,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "provider")
    val provider: String,
    @field:Json(name = "profileImageUrl")
    val profileImageUrl: String,
    @field:Json(name = "agreeAlarm")
    val agreeAlarm: Boolean
): DomainMapper<User> {
    override fun toDomainModel(): User {
        return User(
            id = id,
            nickname = nickname,
            email = email,
            provider = provider,
            profileImageUrl = profileImageUrl,
            agreeAlarm = agreeAlarm
        )
    }
}