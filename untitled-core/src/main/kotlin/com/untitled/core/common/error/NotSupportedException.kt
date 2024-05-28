package com.untitled.core.common.error

data class NotSupportedException(
    override val message: String,
    override val errorCode: ErrorCode = ErrorCode.E501_NOT_SUPPORTED,
    override val cause: Throwable? = null,
) : UnTitledBaseException(message = message, errorCode = errorCode, cause = cause)
