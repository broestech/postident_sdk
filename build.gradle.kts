import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


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
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
    implementation("com.thinkinglogic.builder:kotlin-builder-annotation:1.2.1")
    kapt("com.thinkinglogic.builder:kotlin-builder-processor:1.2.1")
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