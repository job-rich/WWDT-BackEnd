package com.wwdt.auth.domain

interface EditService {
    fun changePassword(changeVo: PasswordChange): Boolean

    fun resetPassword(email: String): Boolean

    fun changeName(changeVo: NameChange): Boolean

    fun changeRole(): Unit
}