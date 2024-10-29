package com.wwdt.auth

import com.wwdt.auth.application.module.EditModule
import com.wwdt.auth.domain.PasswordChange
import com.wwdt.auth.domain.User
import com.wwdt.auth.infra.UserRepository
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
@ExtendWith(MockitoExtension::class)
class EditModuleTest(
    @Mock private val userRepo: UserRepository,
    @Mock private val passwordEncoder: PasswordEncoderWrapper,
) {
    private val editModule: EditModule = EditModule(userRepo, passwordEncoder)
    private val user = User(email = "dummy@test.com", password = "oldPassword", name = "test")
    @Test
    fun `비밀번호 변경 성공`() {
        // given
        val changeVo = PasswordChange(email = "dummy@test.com", oldPassword = "oldPassword", newPassword = "newPassword")

        // when
        `when`(userRepo.findByEmail(changeVo.email)).thenReturn(user)
        `when`(passwordEncoder.matches(changeVo.oldPassword, user.password)).thenReturn(true)
        `when`(passwordEncoder.encode(changeVo.newPassword)).thenReturn("encodedPassword")
        `when`(userRepo.save(user)).thenReturn(user)

        val result = editModule.changePassword(changeVo)

        // then
        assertThat(result).isTrue()
        assertThat(user.password).isEqualTo("encodedPassword")
        assertThat(userRepo.save(user)).isEqualTo(user)
    }
    @Test
    fun `비밀번호 변경 실패`() {
        // given
        val changeVo = PasswordChange(email = "dummy@test.com", oldPassword = "oldPassword", newPassword = "newPassword")

        // when
        `when`(userRepo.findByEmail(changeVo.email)).thenReturn(user)
        `when`(passwordEncoder.matches(changeVo.oldPassword, user.password)).thenReturn(false)

        // then
        assertThatThrownBy { editModule.changePassword(changeVo) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("password is incorrect")
    }
}

