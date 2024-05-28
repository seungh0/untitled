package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.Member

fun interface MemberLoadByIdPort {

    fun findById(memberId: Long): Member?

}
