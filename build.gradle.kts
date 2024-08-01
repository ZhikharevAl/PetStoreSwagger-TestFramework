plugins {
    kotlin("jvm") version "1.9.23"
    id("io.qameta.allure") version "2.12.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val restAssuredVersion = "5.5.0"

dependencies {
    testImplementation(kotlin("test"))

    implementation("com.google.code.gson:gson:2.8.8")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")

    // REST Assured
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
