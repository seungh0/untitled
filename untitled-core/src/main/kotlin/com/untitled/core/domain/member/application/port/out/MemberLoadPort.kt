package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.SocialType

interface MemberLoadPort {

    fun findById(memberId: Long): Member?

    fun findBySocial(socialId: String, socialType: SocialType): Member?

    fun existsBySocial(socialId: String, socialType: SocialType): Boolean

}
