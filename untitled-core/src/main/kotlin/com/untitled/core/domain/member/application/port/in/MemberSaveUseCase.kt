package com.untitled.core.domain.member.application.port.`in`

import com.untitled.core.domain.member.domain.Member

fun interface MemberSaveUseCase {

    fun createMember(request: MemberCreateRequest): Member

}
