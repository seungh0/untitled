package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.Member

fun interface MemberSavePort {

    fun save(member: Member): Member

}
