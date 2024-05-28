package com.untitled.core.infrastructure.thirdparty

import org.springframework.stereotype.Service

@Service
class DummyAuthenticationClient : AuthenticationClient {

    override fun handleAuthentication(authToken: String): String {
        return authToken
    }

}
