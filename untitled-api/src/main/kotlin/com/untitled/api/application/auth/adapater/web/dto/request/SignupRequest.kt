package com.untitled.api.application.auth.adapater.web.dto.request

import com.untitled.core.domain.member.domain.SocialType
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
    @field:NotBlank
    val authToken: String = "",

    val socialType: SocialType,

    @field:NotBlank
    val name: String = "",
)
