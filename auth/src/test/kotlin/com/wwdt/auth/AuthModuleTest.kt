package com.wwdt.auth

import com.wwdt.auth.application.module.AccountModule
import com.wwdt.auth.domain.RegistrationUser
import com.wwdt.auth.domain.Role
import com.wwdt.auth.domain.User
import com.wwdt.auth.domain.UserRole
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

class AuthModuleTest {
    private val userRepo = mock(UserRepository::class.java)
    private val roleRepo = mock(RoleRepository::class.java)
    private val passwordEncoder = mock(PasswordEncoderWrapper::class.java)
    private val authModule = AccountModule(userRepo, roleRepo, passwordEncoder)

    @Test
    fun `신규 가입 유저 성공적으로 등록`() {
        // given
        val registerVo = RegistrationUser(email = "test@example.com", password = "password", name = "test")
        val encodedPassword = "encodedPassword"
        val basicRole = Role(type = RoleGrant.ROLE_USER)
        val registerUser = User(email = registerVo.email, password = encodedPassword, name = registerVo.name)
        val userRole = UserRole(user = registerUser, role = basicRole)
        registerUser.roles.add(userRole)

        // when
        `when`(userRepo.existsByEmail(registerVo.email)).thenReturn(false)
        `when`(roleRepo.findRoleByType(RoleGrant.ROLE_USER)).thenReturn(basicRole)
        `when`(passwordEncoder.encode(registerVo.password)).thenReturn(encodedPassword)
        `when`(userRepo.save(registerUser)).thenReturn(registerUser)

        val result = authModule.registerUser(registerVo)

        // then
        assertThat(result).isTrue()
        verify(userRepo).existsByEmail(registerVo.email)
        verify(roleRepo).findRoleByType(RoleGrant.ROLE_USER)
        verify(passwordEncoder).encode(registerVo.password)

        val userCaptor = ArgumentCaptor.forClass(User::class.java)
        verify(userRepo).save(userCaptor.capture())
        val savedUser = userCaptor.value
        assertThat(savedUser.email).isEqualTo(registerVo.email)

    }
    @Test
    fun `이미 가입된 유저가 가입 시도할 경우 예외 발생`() {
        // given
        val registerVo = RegistrationUser(email = "test@example.com", password = "password", name = "test")

        // when
        `when`(userRepo.existsByEmail(registerVo.email)).thenReturn(true)

        // then
        assertThatThrownBy { authModule.registerUser(registerVo) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Email already exists")
        verify(userRepo).validateExistByEmail(registerVo.email)
        verify(userRepo, never()).save(any(User::class.java))
    }

    @Test
    fun `이메일체크시 이메일이 없을 경우`() {
        // given
        val email = "test@example.com"

        // when
        `when`(userRepo.existsByEmail(email)).thenReturn(false)

        // then
        val result = authModule.isExistEmail(email)
        assertThat(result).isFalse()
        verify(userRepo).validateExistByEmail(email)
    }

    @Test
    fun `이메일 체크시 이메일이 존재할 경우`() {
        // given
        val email = "test@example.com"

        // when
        `when`(userRepo.existsByEmail(email)).thenReturn(true)

        // then
        val result = authModule.isExistEmail(email)
        assertThat(result).isTrue()
        verify(userRepo).validateExistByEmail(email)
    }
}