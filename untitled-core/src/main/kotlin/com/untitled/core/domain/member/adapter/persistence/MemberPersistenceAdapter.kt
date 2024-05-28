package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.member.application.port.out.MemberLoadByIdPort
import com.untitled.core.domain.member.application.port.out.MemberLoadBySocialPort
import com.untitled.core.domain.member.application.port.out.MemberSavePort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberSocialType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
) : MemberSavePort, MemberLoadBySocialPort, MemberLoadByIdPort {

    @Transactional
    override fun saveMember(member: Member): Member {
        val entity = memberRepository.save(member.toEntity())
        return Member.from(member = entity)
    }

    @Transactional(readOnly = true)
    override fun existsBySocial(socialId: String, socialType: MemberSocialType): Boolean {
        return memberRepository.existsBySocial(social = MemberSocial(socialId = socialId, socialType = socialType))
    }

    @Transactional(readOnly = true)
    override fun findById(memberId: Long): Member? {
        return memberRepository.findByIdOrNull(memberId)?.let { Member.from(it) }
    }

}
