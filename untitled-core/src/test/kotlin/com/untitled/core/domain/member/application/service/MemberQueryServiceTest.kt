package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.out.MemberLoadPort
import com.untitled.core.domain.member.domain.MemberFixture
import com.untitled.core.domain.member.domain.MemberNotExistsException
import com.untitled.core.domain.member.domain.SocialType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class MemberQueryServiceTest : StringSpec({

    val memberLoadPort = mockk<MemberLoadPort>()
    val memberQueryService = MemberQueryService(memberLoadPort)

    "멤버를 조회합니다" {
        // given
        val socialId = UUID.randomUUID().toString()
        val socialType = SocialType.KAKAO
        val member = MemberFixture.create(socialId = socialId, socialType = socialType)

        every { memberLoadPort.findBySocial(socialId, socialType) } returns member

        // when
        val sut = memberQueryService.getMember(
            socialId = socialId,
            socialType = socialType,
        )

        // then
        sut shouldBe member
    }

    "해당하는 멤버가 존재하지 않는 경우 조회에 실패합니다" {
        // given
        val socialId = UUID.randomUUID().toString()
        val socialType = SocialType.KAKAO

        every { memberLoadPort.findBySocial(socialId, socialType) } returns null

        // when & then
        shouldThrowExactly<MemberNotExistsException> {
            memberQueryService.getMember(
                socialId = socialId,
                socialType = socialType,
            )
        }
    }

})
