package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.member.application.port.out.MemberLoadPort
import com.untitled.core.domain.member.application.port.out.MemberSavePort
import com.untitled.core.domain.member.application.port.out.MemberUpdatePort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberNotExistsException
import com.untitled.core.domain.member.domain.SocialType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
) : MemberSavePort, MemberUpdatePort, MemberLoadPort {

    @Transactional
    override fun save(member: Member): Member {
        val entity = memberRepository.save(member.toEntity())
        return Member.from(member = entity)
    }

    @Transactional
    override fun patch(memberId: Long, name: String?): Member {
        val entity = memberRepository.findByIdOrNull(memberId)
            ?: throw MemberNotExistsException("멤버($memberId)는 존재하지 않습니다")

        if (name.isNullOrBlank()) {
            return Member.from(entity)
        }

        entity.update(name)
        return Member.from(entity)
    }

    @Transactional(readOnly = true)
    override fun findBySocial(socialId: String, socialType: SocialType): Member? {
        return memberRepository.findBySocial(social = MemberSocial(socialId = socialId, socialType = socialType))
            ?.let { Member.from(it) }
    }

    @Transactional(readOnly = true)
    override fun existsBySocial(socialId: String, socialType: SocialType): Boolean {
        return memberRepository.existsBySocial(social = MemberSocial(socialId = socialId, socialType = socialType))
    }

    @Transactional(readOnly = true)
    override fun findById(memberId: Long): Member? {
        return memberRepository.findByIdOrNull(memberId)?.let { Member.from(it) }
    }

}
