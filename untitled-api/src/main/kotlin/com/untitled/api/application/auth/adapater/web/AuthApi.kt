package com.untitled.api.application.auth.adapater.web

import com.untitled.api.application.auth.application.port.`in`.SignUpResponse
import com.untitled.api.application.auth.application.port.`in`.SignupRequest
import com.untitled.api.application.auth.application.service.AuthFacadeService
import com.untitled.api.application.common.dto.response.ApiResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthApi(
    private val authFacadeService: AuthFacadeService,
) {

    @PostMapping("/v1/auth/signup")
    fun signUp(
        @Valid @RequestBody request: SignupRequest,
    ): ApiResponse<SignUpResponse> {
        val response = authFacadeService.signup(request = request)
        return ApiResponse.ok(response)
    }

}
