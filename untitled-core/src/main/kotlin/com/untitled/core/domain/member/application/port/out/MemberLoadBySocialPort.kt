package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.MemberSocialType

fun interface MemberLoadBySocialPort {

    fun existsBySocial(socialId: String, socialType: MemberSocialType): Boolean

}
