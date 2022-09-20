import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.13.4"
val builderVersion = "1.2.1"
val loggerVersion = "2.0.11"

plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.thinkinglogic.builder:kotlin-builder-annotation:$builderVersion")
    kapt("com.thinkinglogic.builder:kotlin-builder-processor:$builderVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$loggerVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.broeskamp"
            artifactId = "postident_sdk"
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}