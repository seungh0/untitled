package com.untitled.core.domain.member.domain

import com.untitled.core.common.error.ErrorCode
import com.untitled.core.common.error.UnTitledBaseException

data class MemberAlreadyExistsException(
    override val message: String,
    override val cause: Throwable? = null,
) : UnTitledBaseException(message = message, errorCode = ErrorCode.E409_ALREADY_EXISTS_MEMBER, cause = cause)

