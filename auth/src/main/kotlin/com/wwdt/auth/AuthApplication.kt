package com.wwdt.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(scanBasePackages = ["com.wwdt.shared_kernel"])
@EnableJpaAuditing
class AuthApplication

fun main(args: Array<String>) {
    runApplication<AuthApplication>(*args)
}
