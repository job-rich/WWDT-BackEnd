package com.wwdt.auth.application.module

import com.wwdt.auth.domain.EditService
import com.wwdt.auth.domain.ChangeName
import com.wwdt.auth.domain.ChangePassword
import com.wwdt.auth.domain.ResetPassword
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.findUserByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.springframework.stereotype.Component

@Component
class EditModule(
    private val userRepository: UserRepository,
    private val passwordEncoderWrapper: PasswordEncoderWrapper
): EditService{
    override fun changePassword(changeVo: ChangePassword): Boolean {
        val user = userRepository.findUserByEmail(changeVo.email)
        check(passwordEncoderWrapper.matches(changeVo.oldPassword, user.password)) { "password is incorrect" }
        user.password = passwordEncoderWrapper.encode(changeVo.newPassword)
        userRepository.save(user)
        return true
    }

    override fun resetPassword(resetVo: ResetPassword): String {
        val user = userRepository.findUserByEmail(resetVo.email)
        check(user.name == resetVo.name) { "name is incorrect" }
        val newPassword = (1..12)
            .map { ('a'..'z') + ('A'..'Z') + ('0'..'9') }
            .flatten()
            .shuffled()
            .take(12)
            .joinToString("")
        user.password = passwordEncoderWrapper.encode(newPassword)
        userRepository.save(user)
        return newPassword
    }

    override fun changeName(changeVo: ChangeName): Boolean {
        TODO("Not yet implemented")
    }

}