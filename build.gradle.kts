import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonVersion = "2.13.4"
val builderVersion = "1.2.1"
val mockkVersion = "1.12.7"
val loggingVersion = "2.0.2"
val sshjVersion = "0.34.0"
val joseJwtVersion = "9.25.4"
val codeArtifactToken: String? = System.getenv("CODEARTIFACT_AUTH_TOKEN")
project.group = "com.broeskamp"

java {
    withSourcesJar()
    withJavadocJar()
}

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
    id("maven-publish")
    id("signing")
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
    testImplementation("io.mockk:mockk:$mockkVersion")
    implementation("org.slf4j:slf4j-api:$loggingVersion")
    implementation("com.hierynomus:sshj:$sshjVersion")
    implementation("com.nimbusds:nimbus-jose-jwt:$joseJwtVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

if(codeArtifactToken == null || codeArtifactToken == ""){
    project.logger.warn("Codeartifact Auth Token is null or empty.")
} else {
    project.logger.info("Successfully found Codeartifact Auth Token.")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "postident_sdk"
            from(components["java"])
            pom {
                description.set("Kotlin / JVM SDK for PostIdent and eSignature by Deutsche Post.")
                packaging = "jar"
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/broestech/postident_sdk/blob/production/LICENSE.md")
                        distribution.set("repository")
                    }
                    scm {
                        connection.set("scm:git:git://github.com/broestech/postident_sdk.git")
                        developerConnection.set("scm:git:ssh://github.com/broestech/postident_sdk.git")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "Internal"
            url = uri("https://broeskamp-843115942280.d.codeartifact.eu-central-1.amazonaws.com/maven/com.broeskamp.common/")
            credentials {
                username = "aws"
                password = codeArtifactToken
            }
        }
        maven {
            name = "MavenCentral"
            credentials {
                username = System.getenv("OSSRH_USERNAME") ?: project.properties["ossrhUsername"] as String?
                password = System.getenv("OSSRH_PASSWORD") ?: project.properties["ossrhPassword"] as String?

                if (username!!.isEmpty()) {
                    project.logger.error("Username for maven central is empty")
                }
                if (password!!.isEmpty()) {
                    project.logger.error("Password for maven central is empty")
                }
            }
            url = if (project.version.toString().contains("SNAPSHOT"))
                uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            else
                uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        }
    }
}


signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}