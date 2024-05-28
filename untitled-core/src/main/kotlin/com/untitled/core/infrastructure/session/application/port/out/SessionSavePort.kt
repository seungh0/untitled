package com.untitled.core.infrastructure.session.application.port.out

import com.untitled.core.infrastructure.session.domain.MemberSession

fun interface SessionSavePort {

    fun saveSession(session: MemberSession): String

}
