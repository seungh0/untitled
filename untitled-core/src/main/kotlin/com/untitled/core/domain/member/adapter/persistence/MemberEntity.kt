package com.untitled.core.domain.member.adapter.persistence

import com.untitled.core.domain.common.BaseTimeEntity
import com.untitled.core.domain.member.domain.MemberType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Enumerated(EnumType.STRING)
    val memberType: MemberType,

    var name: String,

    @Embedded
    val social: MemberSocial,
) : BaseTimeEntity() {

    fun update(name: String) {
        this.name = name
    }

}
