package com.untitled.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.untitled.core.common.Jsons
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return Jsons.DEFAULT_OBJECT_MAPPER
    }

}
