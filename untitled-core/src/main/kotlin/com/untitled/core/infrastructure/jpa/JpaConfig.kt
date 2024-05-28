package com.untitled.core.infrastructure.jpa

import com.untitled.CoreRoot
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackageClasses = [CoreRoot::class])
@EnableJpaRepositories(basePackageClasses = [CoreRoot::class])
@EnableJpaAuditing
@Configuration
class JpaConfig
