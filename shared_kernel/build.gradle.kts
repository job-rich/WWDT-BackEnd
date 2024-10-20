import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

val jar: Jar by tasks
val bootJar: BootJar by tasks
val bootTestRun: BootRun by tasks

jar.enabled = true
bootJar.enabled = false


allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
