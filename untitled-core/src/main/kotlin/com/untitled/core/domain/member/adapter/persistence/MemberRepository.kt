package com.untitled.core.domain.member.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {

    fun findBySocial(social: MemberSocial): MemberEntity?

    fun existsBySocial(social: MemberSocial): Boolean

}
