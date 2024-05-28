package com.untitled.core.domain.authentication.application.port.`in`

fun interface AuthenticationClient {

    fun handleAuthentication(authToken: String): String

}
