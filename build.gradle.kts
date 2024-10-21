import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.4" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
    kotlin("plugin.spring") version "1.9.23" apply false
    kotlin("plugin.jpa") version "1.9.23" apply false
    kotlin("jvm") version "1.9.23"
}

allprojects{
    group = "com.wwdt"
    version = "0.0.1-SNAPSHOT"

    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
        maven { url = uri("https://repo1.maven.org/maven2")}
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "21"
        targetCompatibility = "21"

    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

val swaggerVersion = "2.5.0"
val loggerVersion = "7.0.0"

subprojects{
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        // Logging
        implementation("io.github.oshai:kotlin-logging-jvm:$loggerVersion")
        // Database
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")

        // Swagger
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swaggerVersion")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")

    }
}
