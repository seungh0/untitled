package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.Member

fun interface MemberUpdatePort {

    fun patch(memberId: Long, name: String?): Member

}
