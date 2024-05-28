package com.untitled.core.infrastructure.session.application.port.out

import com.untitled.core.infrastructure.session.domain.MemberSession

fun interface SessionLoadPort {

    fun loadSession(sessionId: String): MemberSession?

}
