package com.untitled.core.domain.member.adapter.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long> {

    fun existsBySocial(social: MemberSocial): Boolean

}
