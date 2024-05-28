package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberCreateCommand
import com.untitled.core.domain.member.application.port.`in`.MemberCreateUseCase
import com.untitled.core.domain.member.application.port.out.MemberLoadPort
import com.untitled.core.domain.member.application.port.out.MemberSavePort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.SocialType
import org.springframework.stereotype.Service

@Service
class MemberCreateService(
    private val memberSavePort: MemberSavePort,
    private val memberLoadPort: MemberLoadPort,
) : MemberCreateUseCase {

    override fun createMember(command: MemberCreateCommand): Member {
        validateNotExistsMember(socialId = command.socialId, socialType = command.socialType)
        return memberSavePort.save(member = command.toMember())
    }

    private fun validateNotExistsMember(socialId: String, socialType: SocialType) {
        if (memberLoadPort.existsBySocial(socialId = socialId, socialType = socialType)) {
            throw MemberAlreadyExistsException(message = "이미 소셜($socialId-$socialType)로 가입한 계정이 존재합니다")
        }
    }

}
