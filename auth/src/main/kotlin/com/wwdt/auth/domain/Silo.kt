package com.wwdt.auth.domain

import com.wwdt.shared_kernel.model.BaseEntity
import jakarta.persistence.*



@Entity
@Table(
    name = "t_silo",
    indexes = [Index(name = "idx_silo_name", columnList = "name")]
)
class Silo(
    @JoinColumn(name = "project_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val project: Project,

    @Column(nullable = false, length = 255)
    var name: String,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
): BaseEntity() {
}