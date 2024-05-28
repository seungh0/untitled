package com.untitled.api.application.auth.application.service

import com.untitled.api.application.auth.application.port.`in`.SignupRequest
import com.untitled.api.application.session.application.port.`in`.SessionSaveUseCase
import com.untitled.core.domain.authentication.application.port.`in`.AuthenticationClient
import com.untitled.core.domain.member.application.port.`in`.MemberCreateRequest
import com.untitled.core.domain.member.application.port.`in`.MemberSaveUseCase
import com.untitled.core.domain.member.domain.MemberAlreadyExistsException
import com.untitled.core.domain.member.domain.MemberFixture
import com.untitled.core.domain.member.domain.MemberSocialType
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.util.UUID

class AuthFacadeServiceTest : StringSpec({

    val memberSaveUseCase = mockk<MemberSaveUseCase>()
    val authenticationClient = mockk<AuthenticationClient>()
    val sessionSaveUseCase = mockk<SessionSaveUseCase>()

    val authFacadeService = AuthFacadeService(
        memberSaveUseCase = memberSaveUseCase,
        authenticationClient = authenticationClient,
        sessionSaveUseCase = sessionSaveUseCase,
    )

    "새로운 멤버로 회원가입 합니다" {
        // given
        val request = SignupRequest(
            authToken = "auth-token",
            name = "뾰미 토키",
            socialType = MemberSocialType.KAKAO,
        )
        val member = MemberFixture.create(
            name = request.name,
            socialType = request.socialType,
        )
        val sessionId = UUID.randomUUID().toString()

        every {
            memberSaveUseCase.createMember(
                MemberCreateRequest(
                    name = member.name,
                    socialId = member.socialId,
                    socialType = member.socialType,
                    memberType = member.memberType,
                )
            )
        } returns member
        every { authenticationClient.handleAuthentication(any()) } returns member.socialId
        every { sessionSaveUseCase.saveSession(memberId = member.memberId) } returns sessionId

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
            socialType = MemberSocialType.KAKAO,
        )

        every { memberSaveUseCase.createMember(any()) } throws MemberAlreadyExistsException(message = "이미 존재합니다")
        every { authenticationClient.handleAuthentication(any()) } returns "socialId"

        // when & then
        shouldThrowExactly<MemberAlreadyExistsException> {
            authFacadeService.signup(request)
        }
    }

})
