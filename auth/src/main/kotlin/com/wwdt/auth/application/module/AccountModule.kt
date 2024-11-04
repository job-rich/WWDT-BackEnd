package com.wwdt.auth.application.module

import com.wwdt.auth.domain.AccountService
import com.wwdt.auth.domain.RegistrationUser
import com.wwdt.auth.domain.User
import com.wwdt.auth.domain.ValidationUser
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.findUserByEmail
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccountModule(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoderWrapper,
) : AccountService {
    override fun authenticate(authenticationVo: ValidationUser): User {
        val user = userRepo.findUserByEmail(authenticationVo.email)
        check(passwordEncoder.matches(authenticationVo.password, user.password)) { "Password is incorrect" }
        return user
    }

    @Transactional
    override fun registerUser(registerVo: RegistrationUser): Boolean {
        val registerUser = User(
            email = registerVo.email,
            password = passwordEncoder.encode(registerVo.password),
            name = registerVo.name
        )
        userRepo.save(registerUser)
        return true
    }

    override fun isExistEmail(email: String): Boolean {
        userRepo.validateExistByEmail(email)
        return true
    }

}