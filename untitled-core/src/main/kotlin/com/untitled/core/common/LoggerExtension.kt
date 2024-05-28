package com.untitled.core.common

import mu.KLogger
import mu.KotlinLogging

object LoggerExtension {

    val log: KLogger
        inline get() = KotlinLogging.logger {}

}
