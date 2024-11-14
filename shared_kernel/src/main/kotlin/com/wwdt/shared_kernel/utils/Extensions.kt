package com.wwdt.shared_kernel.utils

import io.github.cdimascio.dotenv.dotenv
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

object SecurityExtension {
    fun getUserId(): UUID {
        return UUID.fromString(SecurityContextHolder.getContext().authentication.principal.toString())
    }
}
object EnvExtension {
    private val env = dotenv {
        ignoreIfMissing = true
        ignoreIfMalformed = true
    }
    fun getEnv(key: String): String? = env[key]
}

object ValidationExtension {
    fun isEmailValid(email: String): Boolean {
        return email.isNotBlank() && email.matches(
            Regex("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$")
        )
    }
}