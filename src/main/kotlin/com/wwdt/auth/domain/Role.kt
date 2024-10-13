package com.wwdt.auth.domain

import com.wwdt.auth.domain.enums.RoleGrant
import com.wwdt.shared_kernal.model.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_role",
       indexes = [Index(name = "idx_role_type", columnList = "type")]
)
class Role(
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    val userId: User,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val type: RoleGrant,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val seq: Long = 0
): BaseEntity()