package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberCreateRequest
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.MemberSocialType
import com.untitled.core.domain.member.domain.MemberType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.util.UUID

class MemberServiceTest : StringSpec({

    var existsMember = false

    val memberService = MemberService(
        memberSavePort = { member -> member },
        memberLoadBySocialPort = { _, _ -> existsMember }
    )

    "멤버를 등록합니다" {
        // given
        val request = MemberCreateRequest(
            name = "뽀미 오빠",
            socialType = MemberSocialType.KAKAO,
            socialId = UUID.randomUUID().toString(),
            memberType = MemberType.DEFAULT,
        )

        // when
        val sut = memberService.createMember(request = request)

        // then
        sut.name shouldBe request.name
        sut.memberType shouldBe request.memberType
        sut.socialId shouldBe request.socialId
        sut.socialType shouldBe request.socialType
    }

    "이미 동일한 소셜 정보로 가입한 멤버가 있다면 등록에 실패한다" {
        // given
        existsMember = true

        val request = MemberCreateRequest(
            name = "뽀미 오빠",
            socialType = MemberSocialType.KAKAO,
            socialId = UUID.randomUUID().toString(),
            memberType = MemberType.DEFAULT,
        )

        // when & then
        shouldThrowExactly<MemberAlreadyExistsException> {
            memberService.createMember(request = request)
        }
    }

})
