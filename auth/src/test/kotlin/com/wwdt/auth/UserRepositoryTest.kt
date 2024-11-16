package com.wwdt.auth

import com.wwdt.auth.infra.UserRepository
import com.wwdt.auth.infra.findUserByEmail
import com.wwdt.auth.infra.validateExistByEmail
import com.wwdt.auth.domain.User
import com.wwdt.shared_kernel.core.ConstantsConfig
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    lateinit var userRepository: UserRepository
    @Test
    fun `사용자를 이메일로 조회`() {
        // given
        val user = User(email = ConstantsConfig.DUMMY_EMAIL, password = ConstantsConfig.DUMMY_PASSWORD, name = ConstantsConfig.DUMMY_NAME)
        userRepository.save(user)

        // when
        val foundUser = userRepository.findUserByEmail(ConstantsConfig.DUMMY_EMAIL)

        // then
        assertThat(foundUser).isNotNull
        assertThat(foundUser.email).isEqualTo(ConstantsConfig.DUMMY_EMAIL)
    }
    @Test
    fun `사용자 이메일이 존재하면 예외처리`() {
        // given
        val user = User(email = ConstantsConfig.DUMMY_EMAIL, password = ConstantsConfig.DUMMY_PASSWORD, name = ConstantsConfig.DUMMY_NAME)
        userRepository.save(user)

        // when and then
        assertThatThrownBy { userRepository.validateExistByEmail(ConstantsConfig.DUMMY_EMAIL) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("Email already exists")
    }

    @Test
    fun `사용자 이메일 중복 확인시 예외 처리 하지 않음`() {
        // when and then
        userRepository.validateExistByEmail("nonexistent@example.com")
    }
}