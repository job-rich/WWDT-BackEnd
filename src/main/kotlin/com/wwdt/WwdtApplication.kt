package com.wwdt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WwdtApplication

fun main(args: Array<String>) {
    runApplication<WwdtApplication>(*args)
}
