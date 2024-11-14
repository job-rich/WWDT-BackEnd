package com.wwdt.workspace.api

import com.wwdt.workspace.api.request.CreateProjectDto
import com.wwdt.workspace.application.WorkspaceApplication
import com.wwdt.workspace.domain.Project
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/projects")
class ProjectController(
    private val WorkspaceApplication: WorkspaceApplication
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
    fun createProject(createProjectReq: CreateProjectDto): ResponseEntity<Project> {
        val result = WorkspaceApplication.processCreateProject(createProjectReq)
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