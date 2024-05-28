package com.untitled.api.application.session.application.port.`in`

fun interface SessionSaveUseCase {

    fun saveSession(memberId: Long): String

}
