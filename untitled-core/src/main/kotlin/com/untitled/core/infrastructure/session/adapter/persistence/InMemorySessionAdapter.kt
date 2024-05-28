package com.untitled.core.infrastructure.session.adapter.persistence

import com.untitled.core.infrastructure.session.application.port.out.SessionLoadPort
import com.untitled.core.infrastructure.session.application.port.out.SessionSavePort
import com.untitled.core.infrastructure.session.domain.MemberSession
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InMemorySessionAdapter : SessionSavePort, SessionLoadPort {

    override fun saveSession(session: MemberSession): String {
        val sessionId = UUID.randomUUID().toString()
        sessionRepository[sessionId] = session
        return sessionId
    }

    override fun loadSession(sessionId: String): MemberSession? {
        return sessionRepository[sessionId]
    }

    companion object {
        private val sessionRepository = mutableMapOf<String, MemberSession>()
    }

}
