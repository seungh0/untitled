package com.untitled.core.domain.member.application.service

import com.untitled.core.domain.member.application.port.`in`.MemberModifyCommand
import com.untitled.core.domain.member.application.port.out.MemberUpdatePort
import com.untitled.core.domain.member.domain.MemberFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class MemberModifyServiceTest : FunSpec({

    val memberUpdatePort = mockk<MemberUpdatePort>()
    val memberModifyService = MemberModifyService(memberUpdatePort = memberUpdatePort)

    test("멤버의 정보를 변경합니다") {
        // given
        val name = "뽀미 아니고 키토였어요"
        val origin = MemberFixture.create()

        every { memberUpdatePort.patch(memberId = origin.memberId, name = name) } returns origin.copy(name = name)

        // when
        val sut = memberModifyService.modify(memberId = origin.memberId, command = MemberModifyCommand(name = name))

        // then
        sut shouldBe origin.copy(name = name)
    }

})
