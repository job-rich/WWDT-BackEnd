package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import com.wwdt.domain.entity_deprecated.enums.RoleGrant
import jakarta.persistence.*

@Entity
@Table(name = "t_role")
class Role(
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val name: RoleGrant,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "Role(name='$name', seq=$seq)"
    }
}