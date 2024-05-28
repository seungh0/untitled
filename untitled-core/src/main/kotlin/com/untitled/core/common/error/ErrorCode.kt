package com.untitled.core.common.error

enum class ErrorCode(
    val httpStatusCode: Int,
    val code: String,
) {

    /**
     * 400 Bad Request
     */
    E400_INVALID_ARGUMENTS(httpStatusCode = 400, code = "invalid_arguments"),

    /**
     * 409 Conflict
     */
    E409_ALREADY_EXISTS_MEMBER(httpStatusCode = 409, code = "already_exists_member"),


    /**
     * 500 Internal Server Error
     */
    E500_INTERNAL_ERROR(httpStatusCode = 500, code = "internal_error"),

    /**
     * 501 NotImplemented
     */
    E501_NOT_SUPPORTED(httpStatusCode = 501, code = "not_supported"),

    /**
     * 503 Service UnAvailable
     */
    E503_SERVICE_UNAVAILABLE(httpStatusCode = 503, code = "service_unavailable"),

}
