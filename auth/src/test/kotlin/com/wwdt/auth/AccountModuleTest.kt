package com.wwdt.auth

import com.wwdt.auth.application.module.AccountModule
import com.wwdt.auth.domain.RegistrationUser
import com.wwdt.auth.domain.User
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AccountModuleTest(
    @Mock private val userRepo: UserRepository,
    @Mock private val passwordEncoder: PasswordEncoderWrapper,
) {
    private val accountModule: AccountModule = AccountModule(userRepo, passwordEncoder)

    @Test
    fun `신규 가입 유저 성공적으로 등록`() {
        // given
        val registerVo = RegistrationUser(email = "test@example.com", password = "password", name = "test")
        val encodedPassword = "encodedPassword"

        // when
        `when`(userRepo.existsByEmail(registerVo.email)).thenReturn(false)
        `when`(passwordEncoder.encode(registerVo.password)).thenReturn(encodedPassword)

        val result = accountModule.registerUser(registerVo)

        // then
        assertThat(result).isTrue()
        verify(passwordEncoder).encode(registerVo.password)

        val userCaptor = ArgumentCaptor.forClass(User::class.java)
        verify(userRepo).save(userCaptor.capture())
        val savedUser = userCaptor.value
        assertThat(savedUser.email).isEqualTo(registerVo.email)

    }

    @Test
    fun `이메일체크시 이메일이 없을 경우`() {
        // given
        val email = "test@example.com"

        // when
        `when`(userRepo.existsByEmail(email)).thenReturn(false)

        // then
        val result = accountModule.isExistEmail(email)
        assertThat(result).isTrue()
        verify(userRepo).validateExistByEmail(email)
    }

    @Test
    fun `이메일 체크시 이메일이 존재할 경우`() {
        // given
        val email = "test@example.com"

        // when
        `when`(userRepo.existsByEmail(email)).thenReturn(true)

        // then
        assertThatThrownBy { accountModule.isExistEmail(email) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Email already exists")
        verify(userRepo).validateExistByEmail(email)
    }
}