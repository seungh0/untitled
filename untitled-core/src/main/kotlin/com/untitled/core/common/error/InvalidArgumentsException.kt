package com.untitled.core.common.error

data class InvalidArgumentsException(
    override val message: String,
    override val cause: Throwable? = null,
    override val reasons: List<String>,
) : UnTitledBaseException(
    message = message,
    errorCode = ErrorCode.E400_INVALID_ARGUMENTS,
    cause = cause,
    reasons = reasons
)
