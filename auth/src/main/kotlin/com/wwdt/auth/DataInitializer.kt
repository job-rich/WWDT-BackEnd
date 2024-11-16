package com.wwdt.auth

import com.wwdt.auth.domain.User
import com.wwdt.auth.infra.UserRepository
import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.*

@Component
@Profile("dev", "local")
class DataInitializer {
    @Bean
    fun init(
        passwordEncoderWrapper: PasswordEncoderWrapper,
        userRepository: UserRepository,
        ) = ApplicationRunner {
            if (userRepository.count() == 0L) {
                val user = User(
                    email = ConstantsConfig.DUMMY_EMAIL,
                    password = passwordEncoderWrapper.encode(ConstantsConfig.DUMMY_PASSWORD),
                    name = ConstantsConfig.DUMMY_NAME,
                    id = UUID.fromString(ConstantsConfig.DUMMY_USER_ID)
                )
                userRepository.save(user)
            }
    }
}