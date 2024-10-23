package com.wwdt.auth.application.module

import com.wwdt.auth.domain.*
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.findUserByEmail
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccountModule(
    private val userRepo: UserRepository,
    private val roleRepo: RoleRepository,
    private val passwordEncoder: PasswordEncoderWrapper
): AccountService {
    override fun authenticate(authenticationVo: ValidationUser): User {
        val user = userRepo.findUserByEmail(authenticationVo.email)
        check (passwordEncoder.matches(authenticationVo.password, user.password)) { "Password is incorrect" }
        return user
    }

    @Transactional
    override fun registerUser(registerVo: RegistrationUser): Boolean {
        userRepo.validateExistByEmail(registerVo.email)
        val registerUser = User(
            email = registerVo.email,
            password = passwordEncoder.encode(registerVo.password),
            name = registerVo.name
        )
        val basicRole = roleRepo.findRoleByType(RoleGrant.ROLE_USER)
        val userRole = UserRole(user = registerUser, role = basicRole)
        registerUser.roles.add(userRole)
        userRepo.save(registerUser)
        return true
    }

    override fun isExistEmail(email: String): Boolean {
        userRepo.validateExistByEmail(email)
        return false
    }

}