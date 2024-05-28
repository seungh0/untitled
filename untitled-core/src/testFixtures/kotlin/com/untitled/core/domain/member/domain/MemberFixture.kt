package com.untitled.core.domain.member.domain

import com.untitled.core.support.RandomGenerator.generateEnum
import com.untitled.core.support.RandomGenerator.generateLong
import com.untitled.core.support.RandomGenerator.generateString

object MemberFixture {

    fun create(
        memberId: Long = generateLong(),
        name: String = generateString(),
        memberType: MemberType = generateEnum(MemberType::class.java),
        socialId: String = generateString(),
        socialType: MemberSocialType = generateEnum(MemberSocialType::class.java),
    ) = Member(
        memberId = memberId,
        name = name,
        memberType = memberType,
        socialType = socialType,
        socialId = socialId,
    )

}
