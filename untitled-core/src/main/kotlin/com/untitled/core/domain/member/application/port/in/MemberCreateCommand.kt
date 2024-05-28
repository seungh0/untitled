package com.untitled.core.domain.member.application.port.`in`

import com.untitled.core.common.SelfValidator
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.SocialType
import com.untitled.core.domain.member.domain.MemberType
import jakarta.validation.constraints.NotBlank

data class MemberCreateCommand(
    @field:NotBlank
    val name: String,

    val memberType: MemberType,

    @field:NotBlank
    val socialId: String,

    val socialType: SocialType,
) : SelfValidator<MemberCreateCommand>() {

    init {
        super.validatePersonalCommand()
    }

    fun toMember() = Member(
        name = name,
        memberType = memberType,
        socialType = socialType,
        socialId = socialId,
    )

}
