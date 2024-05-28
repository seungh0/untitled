package com.untitled.core.common.error

data class InternalServerException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.E500_INTERNAL_ERROR,
    override val cause: Throwable? = null,
) : UnTitledBaseException(message = message, errorCode = errorCode, cause = cause)
