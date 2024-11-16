package com.wwdt.workspace.infra

import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.workspace.domain.Permission
import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.enums.WorkspaceType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@DataJpaTest
class PermissionRepositoryTest {
    @Autowired lateinit var permissionRepository: PermissionRepository

    @MockBean lateinit var mockRole: Role

    @Test
    fun `프로젝트 권한 생성 성공`(){
        // given
        val permission = Permission(
            workspaceType = WorkspaceType.PROJECT,
            workspaceId = UUID.randomUUID(),
            userId = UUID.fromString(ConstantsConfig.DUMMY_USER_ID),
            role = mockRole
        )
        val savePermission = permissionRepository.save(permission)

        // when
        val foundPermission = permissionRepository.findPermissionByUserIdAndWorkspaceIdAndWorkspaceType(
            userId = savePermission.userId,
            workspaceId = savePermission.workspaceId,
            workspaceType = WorkspaceType.PROJECT
        )

        // then
        assertThat(foundPermission).isNotNull
        assertThat(foundPermission.id).isEqualTo(savePermission.id)
    }

    @Test
    fun `프로젝트 조회 조건에 맞는 프로젝트가 존재하지 않을 때 조회 실패`(){
        // when and then
        assertThatThrownBy {
            permissionRepository.findPermissionByUserIdAndWorkspaceIdAndWorkspaceType(
                userId = UUID.randomUUID(),
                workspaceId = UUID.randomUUID(),
                workspaceType = WorkspaceType.PROJECT
            )
        }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Permission not found")
    }

    @Test
    fun `사일로 권한 생성 성공`() {
        // given
        val permission = Permission(
            workspaceType = WorkspaceType.SILO,
            workspaceId = UUID.randomUUID(),
            userId = UUID.fromString(ConstantsConfig.DUMMY_USER_ID),
            role = mockRole
        )
        val savePermission = permissionRepository.save(permission)

        // when
        val foundPermission = permissionRepository.findPermissionByUserIdAndWorkspaceIdAndWorkspaceType(
            userId = savePermission.userId,
            workspaceId = savePermission.workspaceId,
            workspaceType = WorkspaceType.SILO
        )

        // then
        assertThat(foundPermission).isNotNull
        assertThat(foundPermission.id).isEqualTo(savePermission.id)
    }

    @Test
    fun `사일로 조회 조건에 맞는 사일로가 존재하지 않을 때 조회 실패`() {
        // when and then
        assertThatThrownBy {
            permissionRepository.findPermissionByUserIdAndWorkspaceIdAndWorkspaceType(
                userId = UUID.randomUUID(),
                workspaceId = UUID.randomUUID(),
                workspaceType = WorkspaceType.SILO
            )
        }
            .isInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Permission not found")
    }
}