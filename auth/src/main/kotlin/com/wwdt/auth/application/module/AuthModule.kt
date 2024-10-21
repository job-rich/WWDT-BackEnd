package com.wwdt.auth.application.module

import com.wwdt.auth.domain.*
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthModule(
    private val userRepo: UserRepository,
    private val roleRepo: RoleRepository,
    private val passwordEncoder: PasswordEncoderWrapper
): AuthService {
    override fun authenticate(authenticationVo: ValidationUser): Boolean {
        TODO("Not yet implemented")
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


}