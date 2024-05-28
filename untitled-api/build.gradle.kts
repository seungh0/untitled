dependencies {
    // core
    implementation(project(":untitled-core"))
    testImplementation(testFixtures(project(":untitled-core")))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
}
