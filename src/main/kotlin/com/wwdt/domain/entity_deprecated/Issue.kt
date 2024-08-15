package com.wwdt.domain.entity_deprecated

import com.wwdt.domain.entity_deprecated.common.BaseEntity
import com.wwdt.domain.entity_deprecated.enums.IssuePriority
import com.wwdt.domain.entity_deprecated.enums.IssueStatus
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "t_issue",
       indexes = [
        Index(name = "idx_issue_status", columnList = "status"),
        Index(name = "idx_issue_priority", columnList = "priority"),
        Index(name = "idx_issue_rank", columnList = "rank"),
        Index(name = "idx_issue_completed_at", columnList = "completedAt")
    ]
)
class Issue(
    // mappedBy는 연관관계의 주인이 아닌 쪽에서 사용한다.
    // mappedBy의 값은 엔티티에 존재하는 속성값으로 사용해야 참조가 가능하다
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    val comments: MutableList<Comment> = mutableListOf(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    val issueProperties: MutableList<IssueProperty> = mutableListOf(),

    @Column
    val title: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    val creator: User,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updater_id")
    val updater: User,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    val assignee: MutableList<IssueAssignee> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: IssueStatus,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val priority: IssuePriority,

    @Column(nullable = false)
    var rank: Int,

    @Column(nullable = false)
    var content: String,

    @Column
    var completedAt: LocalDateTime?,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),

): BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Issue) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Issue(" +
                "comments=$comments, " +
                "title='$title', " +
                "creator=$creator, " +
                "updater=$updater, " +
                "assignee=$assignee, " +
                "status=$status, " +
                "priority=$priority, " +
                "rank=$rank, " +
                "content='$content', " +
                "completedAt=$completedAt, " +
                "id=$id" +
                ")"
    }
}