package com.wwdt.workspace.infra

import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.workspace.domain.Project
import com.wwdt.workspace.domain.Silo
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@DataJpaTest
class SiloRepositoryTest {
    @Autowired lateinit var siloRepository: SiloRepository

    @MockBean lateinit var mockProject: Project

    @Test
    fun `사일로 생성 성공`() {
        // given
        val silo = Silo(
            name = ConstantsConfig.DUMMY_PROJECT_NAME,
            description = ConstantsConfig.DUMMY_PROJECT_DESCRIPTION,
            project = mockProject,
            ownerId = UUID.fromString(ConstantsConfig.DUMMY_USER_ID),
        )
        val saveSilo = siloRepository.save(silo)

        // when
        val foundSilo = siloRepository.findSiloById(saveSilo.id)

        // then
        assertThat(foundSilo).isNotNull
        assertThat(foundSilo.id).isEqualTo(saveSilo.id)
    }

    @Test
    fun `사일로가 존재하지 않을 때 조회 실패`() {
        // when and then
        assertThatThrownBy { siloRepository.findSiloById(UUID.randomUUID()) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Silo not found")
    }
}