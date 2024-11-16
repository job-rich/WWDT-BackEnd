package com.wwdt.workspace.infra

import com.wwdt.workspace.domain.Silo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SiloRepository : JpaRepository<Silo, UUID>

fun SiloRepository.findSiloById(id: UUID): Silo {
    return findByIdOrNull(id) ?: throw NoSuchElementException("Silo not found")
}