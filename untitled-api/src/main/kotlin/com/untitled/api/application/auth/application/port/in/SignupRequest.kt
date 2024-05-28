package com.untitled.api.application.auth.application.port.`in`

import com.untitled.core.domain.member.domain.MemberSocialType
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
    @field:NotBlank
    val authToken: String = "",

    val socialType: MemberSocialType,

    @field:NotBlank
    val name: String = "",
)
