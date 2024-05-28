package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberQueryUseCase
import com.untitled.core.domain.member.application.port.out.MemberLoadPort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberNotExistsException
import com.untitled.core.domain.member.domain.SocialType
import org.springframework.stereotype.Service

@Service
class MemberQueryService(
    private val memberLoadPort: MemberLoadPort,
) : MemberQueryUseCase {

    override fun getMember(socialId: String, socialType: SocialType): Member {
        return memberLoadPort.findBySocial(socialId = socialId, socialType = socialType)
            ?: throw MemberNotExistsException("멤버($socialId-$socialType)는 존재하지 않습니다")
    }

}
