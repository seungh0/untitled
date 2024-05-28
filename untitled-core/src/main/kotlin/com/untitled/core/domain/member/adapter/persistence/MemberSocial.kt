package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.member.domain.SocialType
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class MemberSocial(
    @Enumerated(EnumType.STRING)
    val socialType: SocialType,

    val socialId: String,
)
