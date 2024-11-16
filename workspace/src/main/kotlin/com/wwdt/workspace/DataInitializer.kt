package com.wwdt.workspace

import com.wwdt.shared_kernel.core.ConstantsConfig
import com.wwdt.workspace.domain.Permission
import com.wwdt.workspace.domain.Project
import com.wwdt.workspace.domain.Role
import com.wwdt.workspace.domain.Silo
import com.wwdt.workspace.domain.enums.Authority
import com.wwdt.workspace.domain.enums.WorkspaceType
import com.wwdt.workspace.infra.PermissionRepository
import com.wwdt.workspace.infra.ProjectRepository
import com.wwdt.workspace.infra.RoleRepository
import com.wwdt.workspace.infra.SiloRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.util.*

@Component
@Profile("dev", "local")
class DataInitializer {
    @Bean
    fun init(
        permissionRepository: PermissionRepository,
        roleRepository: RoleRepository,
        projectRepository: ProjectRepository,
        siloRepository: SiloRepository,
    ) = ApplicationRunner {
        initializeRoles(roleRepository)
        initializeProjectsAndSilos(projectRepository, siloRepository)
        initializePermissions(permissionRepository, roleRepository, projectRepository, siloRepository)
    }

    private fun initializeRoles(roleRepository: RoleRepository) {
        if (roleRepository.count() == 0L) {
            val roles = listOf(
                Role(authority = Authority.OWNER, description = "Remove Workspace permissions"),
                Role(authority = Authority.MANAGE, description = "Manage Authorization permissions"),
                Role(authority = Authority.WRITE, description = "Write permissions"),
                Role(authority = Authority.READ, description = "Read permissions")
            )
            roleRepository.saveAll(roles)
        }
    }

    private fun initializeProjectsAndSilos(
        projectRepository: ProjectRepository,
        siloRepository: SiloRepository,
    ) {
        if (projectRepository.count() == 0L && siloRepository.count() == 0L) {
            val project = Project(
                name = ConstantsConfig.DUMMY_PROJECT_NAME,
                description = ConstantsConfig.DUMMY_PROJECT_DESCRIPTION,
                ownerId = UUID.fromString(ConstantsConfig.DUMMY_USER_ID)
            )
            projectRepository.save(project)
            val silo = Silo(
                name = ConstantsConfig.DUMMY_SILO_NAME,
                description = ConstantsConfig.DUMMY_SILO_DESCRIPTION,
                project = project,
                ownerId = project.ownerId
            )
            siloRepository.save(silo)
        }
    }

    private fun initializePermissions(
        permissionRepository: PermissionRepository,
        roleRepository: RoleRepository,
        projectRepository: ProjectRepository,
        siloRepository: SiloRepository,
    ) {
        if (permissionRepository.count() == 0L) {
            val roles = roleRepository.findAll()
            val project = projectRepository.findAll().first()
            val silo = siloRepository.findAll().first()
            val permissions = roles.flatMap { role ->
                listOf(
                    Permission(
                        role = role,
                        workspaceType = WorkspaceType.PROJECT,
                        workspaceId = project.id,
                        userId = project.ownerId
                    ),
                    Permission(
                        role = role,
                        workspaceType = WorkspaceType.SILO,
                        workspaceId = silo.id,
                        userId = silo.ownerId
                    )
                )
            }
            permissionRepository.saveAll(permissions)
        }
    }
}
