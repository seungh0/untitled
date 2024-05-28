package com.untitled.api.application.auth.application.service

import com.untitled.api.application.auth.adapater.web.dto.request.LoginRequest
import com.untitled.api.application.auth.adapater.web.dto.request.SignupRequest
import com.untitled.api.application.auth.adapater.web.dto.response.LoginResponse
import com.untitled.core.infrastructure.session.application.port.out.SessionSavePort
import com.untitled.core.infrastructure.session.domain.MemberSession
import com.untitled.core.infrastructure.thirdparty.AuthenticationClient
import com.untitled.core.domain.member.application.port.`in`.MemberCreateCommand
import com.untitled.core.domain.member.application.port.`in`.MemberCreateUseCase
import com.untitled.core.domain.member.application.port.`in`.MemberQueryUseCase
import com.untitled.core.domain.member.domain.Member
import com.untitled.core.domain.member.domain.SocialType
import com.untitled.core.domain.member.domain.MemberType
import org.springframework.stereotype.Service

@Service
class AuthFacadeService(
    private val memberSaveUseCase: MemberCreateUseCase,
    private val memberQueryUseCase: MemberQueryUseCase,
    private val authenticationClient: AuthenticationClient,
    private val sessionSavePort: SessionSavePort,
) {

    fun signup(request: SignupRequest): LoginResponse {
        val socialId = fetchSocialId(authToken = request.authToken, socialType = request.socialType)
        val member = memberSaveUseCase.createMember(
            command = MemberCreateCommand(
                name = request.name,
                socialType = request.socialType,
                socialId = socialId,
                memberType = MemberType.DEFAULT,
            )
        )
        return saveSession(member = member)
    }

    fun login(request: LoginRequest): LoginResponse {
        val socialId = fetchSocialId(authToken = request.authToken, socialType = request.socialType)
        val member = memberQueryUseCase.getMember(socialId = socialId, socialType = request.socialType)
        return saveSession(member = member)
    }

    private fun fetchSocialId(authToken: String, socialType: SocialType): String {
        return authenticationClient.handleAuthentication(authToken = authToken)
    }

    private fun saveSession(member: Member): LoginResponse {
        val sessionId = sessionSavePort.saveSession(session = MemberSession(memberId = member.memberId))
        return LoginResponse(token = sessionId)
    }

}
