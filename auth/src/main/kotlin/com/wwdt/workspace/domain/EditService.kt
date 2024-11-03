package com.wwdt.workspace.domain

interface EditService {
    fun changePassword(changeVo: ChangePassword): Boolean

    fun resetPassword(resetVo: ResetPassword): String

    fun changeName(changeVo: ChangeName): Boolean

}