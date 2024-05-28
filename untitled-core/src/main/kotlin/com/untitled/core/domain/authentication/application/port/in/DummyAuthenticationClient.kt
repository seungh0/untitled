package com.untitled.core.domain.authentication.application.port.`in`

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DummyAuthenticationClient : AuthenticationClient {

    override fun handleAuthentication(authToken: String): String {
        return UUID.randomUUID().toString()
    }

}
