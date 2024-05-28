package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberCreateRequest
import com.untitled.core.domain.member.application.port.`in`.MemberSaveUseCase
import com.untitled.core.domain.member.application.port.out.MemberLoadBySocialPort
import com.untitled.core.domain.member.application.port.out.MemberSavePort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.MemberSocialType
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberSavePort: MemberSavePort,

    private val memberLoadBySocialPort: MemberLoadBySocialPort,
) : MemberSaveUseCase {

    override fun createMember(request: MemberCreateRequest): Member {
        validateNotExistsSocial(socialId = request.socialId, socialType = request.socialType)
        return memberSavePort.saveMember(member = request.toMember())
    }

    private fun validateNotExistsSocial(socialId: String, socialType: MemberSocialType) {
        if (memberLoadBySocialPort.existsBySocial(socialId = socialId, socialType = socialType)) {
            throw MemberAlreadyExistsException(message = "이미 소셜($socialId-$socialType)로 가입한 계정이 존재합니다")
        }
    }

}
