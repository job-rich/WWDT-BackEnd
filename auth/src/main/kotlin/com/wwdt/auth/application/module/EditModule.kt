package com.wwdt.auth.application.module

import com.wwdt.auth.domain.EditService
import com.wwdt.auth.domain.NameChange
import com.wwdt.auth.domain.PasswordChange
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.findUserByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.stereotype.Component

@Component
class EditModule(
    private val userRepository: UserRepository,
    private val passwordEncoderWrapper: PasswordEncoderWrapper
): EditService{
    override fun changePassword(changeVo: PasswordChange): Boolean {
        val user = userRepository.findUserByEmail(changeVo.email)
        check(passwordEncoderWrapper.matches(changeVo.oldPassword, user.password)) { "password is incorrect" }
        user.password = passwordEncoderWrapper.encode(changeVo.newPassword)
        userRepository.save(user)
        return true
    }

    override fun resetPassword(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeName(changeVo: NameChange): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeRole() {
        TODO("Not yet implemented")
    }
}