package com.untitled.api.application.auth.application.service

import com.untitled.api.application.session.application.port.`in`.SessionSaveUseCase
import com.untitled.api.application.auth.application.port.`in`.SignUpResponse
import com.untitled.api.application.auth.application.port.`in`.SignupRequest
import com.untitled.core.domain.authentication.application.port.`in`.AuthenticationClient
import com.untitled.core.domain.member.domain.MemberType
import com.untitled.core.domain.member.application.port.`in`.MemberCreateRequest
import com.untitled.core.domain.member.application.port.`in`.MemberSaveUseCase
import org.springframework.stereotype.Service

@Service
class AuthFacadeService(
    private val memberSaveUseCase: MemberSaveUseCase,
    private val authenticationClient: AuthenticationClient,
    private val sessionSaveUseCase: SessionSaveUseCase,
) {

    fun signup(request: SignupRequest): SignUpResponse {
        val socialId = authenticationClient.handleAuthentication(authToken = request.authToken)
        val member = memberSaveUseCase.createMember(
            request = MemberCreateRequest(
                name = request.name,
                socialType = request.socialType,
                socialId = socialId,
                memberType = MemberType.DEFAULT,
            )
        )
        val sessionId = sessionSaveUseCase.saveSession(memberId = member.memberId)
        return SignUpResponse(token = sessionId)
    }

}
