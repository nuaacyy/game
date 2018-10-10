package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_REPLY_NAMED_QUERY = "findAllianceReply"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_REPLY_NAMED_QUERY,
        // language=HQL
        query = "from AllianceReplyEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_replies")
data class AllianceReplyEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_REPLIES_ALLIANCE_ID")
    override val allianceId: Long,

    @Column(name = "topic_id", nullable = false)
    var topicId: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "message", nullable = false)
    var message: String,

    @Column(name = "reply_at", nullable = false)
    var replyAt: Long

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, "", 0)

    override fun primaryKey() = id
}
