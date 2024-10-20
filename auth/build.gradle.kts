import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

jar.enabled = false
bootJar.enabled = true


dependencies {
    implementation(project(":shared_kernel"))
    // Database
    implementation("org.postgresql:postgresql")
}

tasks.test {
    useJUnitPlatform()
}