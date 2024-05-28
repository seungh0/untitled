tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

val kotlinLoggingJvmVersion: String by project.extra

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    // jpa
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // db
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")

    // json
    implementation("org.springframework.boot:spring-boot-starter-json")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // logging
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingJvmVersion")
}
