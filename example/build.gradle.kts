import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

project.group = "com.broeskamp"

java {
    withSourcesJar()
    withJavadocJar()
}

plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation("com.broeskamp:postident_sdk:1.1.0")
    testImplementation(kotlin("test"))
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}
