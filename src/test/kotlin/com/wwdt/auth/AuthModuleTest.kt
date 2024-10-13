package com.wwdt.auth

import com.wwdt.auth.api.request.RegisterUserDto
import com.wwdt.auth.application.module.AuthModule
import com.wwdt.auth.domain.RegistrationUser
import com.wwdt.auth.domain.Role
import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.auth.infra.RoleRepository
import com.wwdt.auth.infra.UserRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*


class AuthModuleTest {
    private val userRepo = mock(UserRepository::class.java)
    private val roleRepo = mock(RoleRepository::class.java)
    private val authModule = AuthModule(userRepo, roleRepo)

    @Test
    fun `신규 가입 유저 성공적으로 등록`() {
        // given
        val registerVo = RegistrationUser(email="test@example.com", password="password", name="test")
        // when
        `when`(userRepo.existsByEmail(registerVo.email)).thenReturn(false)
        `when`(roleRepo.findRoleByType(RoleGrant.ROLE_USER)).thenReturn(Role(type = RoleGrant.ROLE_USER))
        `when`(userRepo.save(registerVo.toUser())).thenReturn(registerVo.toUser())
        val result = authModule.registerUser(registerVo)
        // then
        assertThat(result).isTrue()
        verify(userRepo).existsByEmail(registerVo.email)

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
        verify(userRepo).existsByEmail(registerVo.email)
        verify(userRepo, never()).save(registerVo.toUser())
    }

}