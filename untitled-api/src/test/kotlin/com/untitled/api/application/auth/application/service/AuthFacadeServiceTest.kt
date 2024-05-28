package com.untitled.api.application.auth.application.service

import com.untitled.api.application.auth.adapater.web.dto.request.SignupRequest
import com.untitled.core.infrastructure.session.application.port.out.SessionSavePort
import com.untitled.core.infrastructure.session.domain.MemberSession
import com.untitled.core.infrastructure.thirdparty.AuthenticationClient
import com.untitled.core.domain.member.application.port.`in`.MemberCreateCommand
import com.untitled.core.domain.member.application.port.`in`.MemberCreateUseCase
import com.untitled.core.domain.member.application.port.`in`.MemberQueryUseCase
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.MemberFixture
import com.untitled.core.domain.member.domain.SocialType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class AuthFacadeServiceTest : StringSpec({

    val memberSaveUseCase = mockk<MemberCreateUseCase>()
    val memberQueryUseCase = mockk<MemberQueryUseCase>()
    val authenticationClient = mockk<AuthenticationClient>()
    val sessionSaveUseCase = mockk<SessionSavePort>()

    val authFacadeService = AuthFacadeService(
        memberSaveUseCase = memberSaveUseCase,
        memberQueryUseCase = memberQueryUseCase,
        authenticationClient = authenticationClient,
        sessionSavePort = sessionSaveUseCase,
    )

    "새로운 멤버로 회원가입 합니다" {
        // given
        val request = SignupRequest(
            authToken = "auth-token",
            name = "뾰미 토키",
            socialType = SocialType.KAKAO,
        )
        val member = MemberFixture.create(
            name = request.name,
            socialType = request.socialType,
        )
        val sessionId = UUID.randomUUID().toString()

        every {
            memberSaveUseCase.createMember(
                MemberCreateCommand(
                    name = member.name,
                    socialId = member.socialId,
                    socialType = member.socialType,
                    memberType = member.memberType,
                )
            )
        } returns member
        every { authenticationClient.handleAuthentication(any()) } returns member.socialId
        every { sessionSaveUseCase.saveSession(session = MemberSession(memberId = member.memberId)) } returns sessionId

        // when
        val sut = authFacadeService.signup(request)

        // then
        sut.token shouldBe sessionId
    }

    "이미 동일한 소셜 정보로 가입한 멤버가 있으면 회원가입에 실패한다" {
        // given
        val request = SignupRequest(
            authToken = "auth-token",
            name = "뾰미 토키",
            socialType = SocialType.KAKAO,
        )

        every { memberSaveUseCase.createMember(any()) } throws MemberAlreadyExistsException(message = "이미 존재합니다")
        every { authenticationClient.handleAuthentication(any()) } returns "socialId"

        // when & then
        shouldThrowExactly<MemberAlreadyExistsException> {
            authFacadeService.signup(request)
        }
    }

})
