package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_issue_task_force")
class IssueTaskForce (
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_force_seq")
    val taskForce: TaskForce,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L

): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IssueTaskForce) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "IssueTaskForce(issue=$issue, taskForce=$taskForce, seq=$seq)"
    }
}