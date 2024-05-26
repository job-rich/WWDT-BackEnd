package com.wwdt.domain.entity

import com.wwdt.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_issue_property")
class IssueProperty(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_seq")
    val properties: Property,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L

) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IssueProperty) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "IssueProperty(" +
                "issue=$issue, " +
                "properties=$properties, " +
                "seq=$seq" +
                ")"
    }
}
