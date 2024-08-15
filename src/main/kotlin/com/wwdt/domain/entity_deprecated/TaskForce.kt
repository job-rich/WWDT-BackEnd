package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "t_task_force",
       indexes = [Index(name = "idx_task_force_completed_at", columnList = "completedAt")]
)
class TaskForce(
    @Column
    var completedAt: LocalDateTime? = null,

    @Column(length = 10000)
    var description: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TaskForce) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "TaskForce(name='$name', seq=$seq, description='$description')"
    }
}