package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberModifyCommand
import com.untitled.core.domain.member.application.port.`in`.MemberModifyUseCase
import com.untitled.core.domain.member.application.port.out.MemberUpdatePort
import com.untitled.core.domain.member.domain.Member
import org.springframework.stereotype.Service

@Service
class MemberModifyService(
    private val memberUpdatePort: MemberUpdatePort,
) : MemberModifyUseCase {

    override fun modify(memberId: Long, command: MemberModifyCommand): Member {
        return memberUpdatePort.patch(memberId = memberId, name = command.name)
    }

}
