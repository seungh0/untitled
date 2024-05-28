package com.untitled.core.domain.member.application.port.out

import com.untitled.core.domain.member.domain.Member

fun interface MemberSavePort {

    fun saveMember(member: Member): Member

}
