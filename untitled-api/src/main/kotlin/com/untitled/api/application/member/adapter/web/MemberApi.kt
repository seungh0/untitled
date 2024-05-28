package com.untitled.api.application.member.adapter.web

import com.untitled.api.application.common.dto.response.ApiResponse
import com.untitled.api.application.member.adapter.web.dto.request.MemberModifyRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApi {

    @PatchMapping("/v1/members")
    fun modifyMember(
        @Valid @RequestBody request: MemberModifyRequest,
    ): ApiResponse<Nothing?> {
        // TODO:
        return ApiResponse.OK
    }

}
