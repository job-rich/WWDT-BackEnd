package com.wwdt.workspace.domain

import com.wwdt.shared_kernel.model.BaseEntity
import com.wwdt.workspace.domain.enums.WorkspaceType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "t_permission",
    indexes = [Index(name = "idx_index_user_id", columnList = "userId")]
)
class Permission(

    @JoinColumn(name = "role_seq")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val role: Role,

    @Column(nullable = false)
    val workspaceId: UUID,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val workspaceType: WorkspaceType,

    @Column(nullable = false)
    val userId: UUID,

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
) : BaseEntity()