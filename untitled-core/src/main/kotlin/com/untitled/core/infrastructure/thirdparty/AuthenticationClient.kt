package com.untitled.core.infrastructure.thirdparty

fun interface AuthenticationClient {

    fun handleAuthentication(authToken: String): String

}
