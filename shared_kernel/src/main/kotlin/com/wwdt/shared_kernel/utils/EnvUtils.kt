package com.wwdt.shared_kernel.utils

import io.github.cdimascio.dotenv.dotenv

object EnvUtils {
    private val env = dotenv {
        ignoreIfMissing = true
        ignoreIfMalformed = true
    }
    fun getEnv(key: String): String? = env[key]
}