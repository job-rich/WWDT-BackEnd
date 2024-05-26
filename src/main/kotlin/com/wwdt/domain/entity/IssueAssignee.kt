package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_issue_assignee")
class IssueAssignee(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val assignee: User,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IssueAssignee) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "IssueAssignee(" +
                "issue=$issue, " +
                "assignee=$assignee, " +
                "seq=$seq" +
                ")"
    }
}