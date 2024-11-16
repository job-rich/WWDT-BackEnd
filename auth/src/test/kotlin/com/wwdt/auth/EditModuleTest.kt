package com.wwdt.auth

import com.wwdt.auth.application.module.EditModule
import com.wwdt.auth.domain.ChangePassword
import com.wwdt.auth.domain.ResetPassword
import com.wwdt.auth.domain.User
import com.wwdt.auth.infra.UserRepository
import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.shared_kernel.infra.PasswordEncoderWrapper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EditModuleTest(
    @Mock private val userRepo: UserRepository,
    @Mock private val passwordEncoder: PasswordEncoderWrapper,
) {
    private val editModule: EditModule = EditModule(userRepo, passwordEncoder)
    private val user = User(email = ConstantsConfig.DUMMY_EMAIL, password = ConstantsConfig.DUMMY_PASSWORD, name = ConstantsConfig.DUMMY_NAME)

    @Test
    fun `비밀번호 변경 성공`() {
        // given
        val changeVo =
            ChangePassword(email = ConstantsConfig.DUMMY_EMAIL, oldPassword = ConstantsConfig.DUMMY_PASSWORD, newPassword = "newPassword")

        // when
        `when`(userRepo.findByEmail(changeVo.email)).thenReturn(user)
        `when`(passwordEncoder.matches(changeVo.oldPassword, user.password)).thenReturn(true)
        `when`(passwordEncoder.encode(changeVo.newPassword)).thenReturn(ConstantsConfig.DUMMY_ENCODED_PASSWORD)
        `when`(userRepo.save(user)).thenReturn(user)

        val result = editModule.changePassword(changeVo)

        // then
        assertThat(result).isTrue()
        assertThat(user.password).isEqualTo(ConstantsConfig.DUMMY_ENCODED_PASSWORD)
        assertThat(userRepo.save(user)).isEqualTo(user)
    }

    @Test
    fun `비밀번호 변경 실패`() {
        // given
        val changeVo =
            ChangePassword(email = ConstantsConfig.DUMMY_EMAIL, oldPassword = ConstantsConfig.DUMMY_PASSWORD, newPassword = "newPassword")

        // when
        `when`(userRepo.findByEmail(changeVo.email)).thenReturn(user)
        `when`(passwordEncoder.matches(changeVo.oldPassword, user.password)).thenReturn(false)

        // then
        assertThatThrownBy { editModule.changePassword(changeVo) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("password is incorrect")
    }

    @Test
    fun `비밀번호 초기화 성공`() {
        // given
        val resetVo = ResetPassword(email = ConstantsConfig.DUMMY_EMAIL, name = ConstantsConfig.DUMMY_NAME)

        // when
        `when`(userRepo.findByEmail(resetVo.email)).thenReturn(user)
        `when`(passwordEncoder.encode(anyString())).thenReturn("encodedRandomPassword")
        `when`(userRepo.save(user)).thenReturn(user)

        val newPassword = editModule.resetPassword(resetVo)

        assertThat(newPassword).isNotBlank()
        assertThat(user.password).isEqualTo("encodedRandomPassword")
    }

    @Test
    fun `비밀번호 초기화 실패 - 유저 이름 불일치`() {
        // given
        val resetVo = ResetPassword(email = ConstantsConfig.DUMMY_EMAIL, name = "wrongName")

        // when
        `when`(userRepo.findByEmail(resetVo.email)).thenReturn(user)

        // then
        assertThatThrownBy { editModule.resetPassword(resetVo) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("name is incorrect")
    }
}

