package com.untitled.core.domain.member.application.port.`in`

import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.SocialType

fun interface MemberQueryUseCase {

    fun getMember(socialId: String, socialType: SocialType): Member

}
