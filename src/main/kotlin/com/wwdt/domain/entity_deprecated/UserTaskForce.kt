package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_user_task_force")
class UserTaskForce(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_force_seq")
    val taskForce: TaskForce,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserTaskForce) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "UserTaskForce(user=$user, taskForce=$taskForce, seq=$seq)"
    }
}