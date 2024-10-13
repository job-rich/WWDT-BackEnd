package com.wwdt.auth.application.module

import com.wwdt.auth.domain.*
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthModule(
    private val userRepo: UserRepository,
    private val roleRepo: RoleRepository
): AuthService {
    override fun authenticate(authenticationVo: ValidationUser): Boolean {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun registerUser(registerVo: RegistrationUser): Boolean {
        validateEmail(registerVo.email)
        val registerUser = registerVo.toUser()
        // TODO: Encrypt password
        val basicRole = roleRepo.findRoleByType(RoleGrant.ROLE_USER)
        val userRole = UserRole(user = registerUser, role = basicRole)
        registerUser.roles.add(userRole)
        userRepo.save(registerUser)
        return true
    }

    private fun validateEmail(email: String) {
        val isExistEmail: Boolean = userRepo.existsByEmail(email)
        check(!isExistEmail) { "Email already exists" }
    }


}