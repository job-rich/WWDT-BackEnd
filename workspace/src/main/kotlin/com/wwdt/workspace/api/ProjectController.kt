package com.wwdt.workspace.api

import com.wwdt.shared_kernel.model.CommonResponse
import com.wwdt.workspace.api.request.CreateProjectDto
import com.wwdt.workspace.application.WorkspaceApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/projects")
class ProjectController(
    private val workspaceApplication: WorkspaceApplication
) {
    @GetMapping
    fun getProjects(): String {
        return "Project List"
    }

    @GetMapping("/{projectId}")
    fun projectDetail(@PathVariable projectId: String): String {
        return "Project Detail"
    }

    @PostMapping
    fun createProject(createProjectReq: CreateProjectDto): ResponseEntity<CommonResponse> {
        val result = workspaceApplication.processCreateProject(createProjectReq)
        return ResponseEntity(result, HttpStatus.CREATED)
    }

    @PatchMapping("/{projectId}")
    fun updateProject(@PathVariable projectId: String): String {
        return "Update Project"
    }

    @DeleteMapping("/{projectId}")
    fun deleteProject(@PathVariable projectId: String): String {
        return "Delete Project"
    }
}