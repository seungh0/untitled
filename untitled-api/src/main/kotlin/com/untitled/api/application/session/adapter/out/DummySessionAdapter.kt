package com.untitled.api.application.session.adapter.out

import com.untitled.api.application.session.application.port.`in`.SessionSaveUseCase
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DummySessionAdapter : SessionSaveUseCase {

    override fun saveSession(memberId: Long): String {
        return UUID.randomUUID().toString()
    }

}
