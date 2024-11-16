package com.wwdt.workspace.infra

import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.workspace.domain.Project
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*

@DataJpaTest
class ProjectRepositoryTest {
    @Autowired
    lateinit var projectRepository: ProjectRepository

    @Test
    fun `프로젝트 생성 성공`() {
        // given
        val project = Project(
            name = ConstantsConfig.DUMMY_PROJECT_NAME,
            description = ConstantsConfig.DUMMY_PROJECT_DESCRIPTION,
            ownerId = UUID.fromString(ConstantsConfig.DUMMY_USER_ID),
        )
        val saveProject = projectRepository.save(project)

        // when
        val foundProject = projectRepository.findProjectById(saveProject.id)

        // then
        assertThat(foundProject).isNotNull
        assertThat(foundProject.id).isEqualTo(saveProject.id)
    }

    @Test
    fun `프로젝트가 존재하지 않을 때 조회 실패`() {
        // when and then
        assertThatThrownBy { projectRepository.findProjectById(UUID.randomUUID()) }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Project not found")
    }

}