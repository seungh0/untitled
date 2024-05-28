package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberCreateCommand
import com.untitled.core.domain.member.application.port.out.MemberLoadPort
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.MemberType
import com.untitled.core.domain.member.domain.SocialType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class MemberCreateServiceTest : StringSpec({

    var existsMember = false

    val memberService = MemberCreateService(
        memberSavePort = { member -> member },
        memberLoadPort = object : MemberLoadPort {
            override fun findById(memberId: Long): Member? {
                TODO("Not yet implemented")
            }

            override fun findBySocial(socialId: String, socialType: SocialType): Member? {
                TODO("Not yet implemented")
            }

            override fun existsBySocial(socialId: String, socialType: SocialType): Boolean {
                return existsMember
            }
        }
    )

    "멤버를 등록합니다" {
        // given
        existsMember = false

        val request = MemberCreateCommand(
            name = "뽀미 오빠",
            socialType = SocialType.KAKAO,
            socialId = UUID.randomUUID().toString(),
            memberType = MemberType.DEFAULT,
        )

        // when
        val sut = memberService.createMember(command = request)

        // then
        sut.name shouldBe request.name
        sut.memberType shouldBe request.memberType
        sut.socialId shouldBe request.socialId
        sut.socialType shouldBe request.socialType
    }

    "이미 동일한 소셜 정보로 가입한 멤버가 있다면 등록에 실패한다" {
        // given
        existsMember = true

        val request = MemberCreateCommand(
            name = "뽀미 오빠",
            socialType = SocialType.KAKAO,
            socialId = UUID.randomUUID().toString(),
            memberType = MemberType.DEFAULT,
        )

        // when & then
        shouldThrowExactly<MemberAlreadyExistsException> {
            memberService.createMember(command = request)
        }
    }

})
