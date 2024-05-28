package com.untitled.core.domain.member.application.port.`in`

import com.untitled.core.domain.member.domain.Member

fun interface MemberModifyUseCase {

    fun modify(memberId: Long, command: MemberModifyCommand): Member

}
