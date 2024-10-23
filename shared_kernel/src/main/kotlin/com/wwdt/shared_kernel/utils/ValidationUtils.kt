package com.wwdt.shared_kernel.utils


object ValidationUtils {
    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && email.matches(
            Regex("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$")
        )
    }
}