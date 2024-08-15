package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_property")
class Property(
    @Column
    val name: String,

    @Column
    val type: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "properties")
    val issueProperties: MutableList<IssueProperty> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_seq")
    val team: Team,

    @Column
    val value: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val seq: Long = 0L
): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Property) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "Property(" +
                "seq=$seq, " +
                "team=$team, " +
                "name='$name', " +
                "type='$type', " +
                "value='$value'" +
                ")"
    }
}