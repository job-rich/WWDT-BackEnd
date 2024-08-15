package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "t_team",
       indexes = [
        Index(name = "idx_team_name", columnList = "name"),
        Index(name = "idx_team_parent_seq", columnList = "parent_seq")
    ]
)
class Team(
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    val teamJoinRequests: MutableList<UserTeamJoinRequest> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_seq")
    val parent: Team? = null,

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
        if (other !is Team) return false

        if (seq != other.seq) return false

        return true
    }

    override fun hashCode(): Int {
        return seq.hashCode()
    }

    override fun toString(): String {
        return "Team(name='$name', seq=$seq, description='$description')"
    }
}