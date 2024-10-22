package com.wwdt.auth

import com.wwdt.auth.domain.Role
import com.wwdt.auth.domain.User
import com.wwdt.auth.domain.UserRole
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
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
        userRepository: UserRepository,
        roleRepository: RoleRepository
        ) = ApplicationRunner {
            if (roleRepository.count() == 0L && userRepository.count() == 0L) {
                val systemAdminRole = roleRepository.save(Role(type = RoleGrant.ROLE_SYSTEM_ADMIN))
                val adminRole = roleRepository.save(Role(type = RoleGrant.ROLE_ADMIN))
                val userRole = roleRepository.save(Role(type = RoleGrant.ROLE_USER))
                val mangerRole = roleRepository.save(Role(type = RoleGrant.ROLE_MANAGER))

                val user = User(
                    email = "dummy@test.com",
                    password = passwordEncoderWrapper.encode("dummy"),
                    name = "dummy"
                )
                user.roles.add(UserRole(user = user, role = userRole))
                userRepository.save(user)
            }
    }
}