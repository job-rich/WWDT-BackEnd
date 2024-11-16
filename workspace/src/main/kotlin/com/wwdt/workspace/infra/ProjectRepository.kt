package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProjectRepository: JpaRepository<Project, UUID>

fun ProjectRepository.findProjectById(id: UUID): Project {
    return findByIdOrNull(id) ?: throw NoSuchElementException("Project not found")
}