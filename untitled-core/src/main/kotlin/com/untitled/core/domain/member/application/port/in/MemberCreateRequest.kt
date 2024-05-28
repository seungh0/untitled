package com.untitled.core.domain.member.application.port.`in`

import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberSocialType
import com.untitled.core.domain.member.domain.MemberType

data class MemberCreateRequest(
    val name: String,
    val memberType: MemberType,
    val socialId: String,
    val socialType: MemberSocialType,
) {

    fun toMember() = Member(
        name = name,
        memberType = memberType,
        socialType = socialType,
        socialId = socialId,
    )

}
