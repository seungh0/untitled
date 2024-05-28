package com.untitled.api.application.auth.adapater.web

import com.untitled.api.application.auth.adapater.web.dto.request.LoginRequest
import com.untitled.api.application.auth.adapater.web.dto.request.SignupRequest
import com.untitled.api.application.auth.adapater.web.dto.response.LoginResponse
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
    ): ApiResponse<LoginResponse> {
        val response = authFacadeService.signup(request = request)
        return ApiResponse.ok(response)
    }

    @PostMapping("/v1/auth/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
    ): ApiResponse<LoginResponse> {
        val response = authFacadeService.login(request = request)
        return ApiResponse.ok(response)
    }

}
