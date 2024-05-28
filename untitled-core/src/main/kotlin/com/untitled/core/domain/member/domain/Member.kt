package com.untitled.core.domain.member.domain

import com.untitled.core.domain.member.adapter.persistence.MemberEntity
import com.untitled.core.domain.member.adapter.persistence.MemberSocial

data class Member(
    val memberId: Long = 0L,
    val name: String,
    val memberType: MemberType,
    val socialId: String,
    val socialType: MemberSocialType,
) {

    fun toEntity() = MemberEntity(
        name = name,
        memberType = memberType,
        social = MemberSocial(socialType = socialType, socialId = socialId)
    )

    companion object {
        fun from(member: MemberEntity) = Member(
            memberId = member.id,
            name = member.name,
            memberType = member.memberType,
            socialType = member.social.socialType,
            socialId = member.social.socialId,
        )
    }

}
