package com.untitled.core.domain.member.domain

import com.untitled.core.common.error.ErrorCode
import com.untitled.core.common.error.UnTitledBaseException

data class MemberNotExistsException(
    override val message: String,
    override val cause: Throwable? = null,
) : UnTitledBaseException(
    message = message,
    errorCode = ErrorCode.E404_NOT_EXISTS_MEMBER,
    cause = cause
)
