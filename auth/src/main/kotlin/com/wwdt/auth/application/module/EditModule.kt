package com.wwdt.auth.application.module

import com.wwdt.auth.domain.EditService
import com.wwdt.auth.domain.NameChange
import com.wwdt.auth.domain.PasswordChange
import org.springframework.stereotype.Component

@Component
class EditModule(

): EditService{
    override fun changePassword(changeVo: PasswordChange): Boolean {
        TODO("Not yet implemented")
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