package com.untitled.core.lib

import io.kotest.common.ExperimentalKotest
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.extensions.spring.SpringExtension

class CoreProjectConfig : AbstractProjectConfig() {
    @ExperimentalKotest
    override val isolationMode = IsolationMode.InstancePerTest
    override fun extensions() = listOf(SpringExtension)
}
