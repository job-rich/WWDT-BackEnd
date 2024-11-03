package com.wwdt.workspace

import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev", "local")
class DataInitializer {
    @Bean
    fun init(
        passwordEncoderWrapper: PasswordEncoderWrapper,
        ) = ApplicationRunner {

    }
}