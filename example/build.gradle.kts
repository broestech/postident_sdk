project.group = "com.broeskamp"

plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    // The actual PostIdent SDK
    implementation("com.broeskamp:postident_sdk:1.1.0")

    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
}
