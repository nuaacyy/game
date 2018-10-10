package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_TOPIC_NAMED_QUERY = "findAllianceTopic"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_TOPIC_NAMED_QUERY,
        // language=HQL
        query = "from AllianceTopicEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "alliance_topics")
data class AllianceTopicEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "ALLIANCE_TOPICS_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "ttype", nullable = false)
    var ttype: Int,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "create_at", nullable = false)
    var createAt: Long,

    @Column(name = "last_at", nullable = false)
    var lastAt: Long,

    @Column(name = "readT", nullable = false)
    var readT: String

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, "", 0, 0, "")

    override fun primaryKey() = id
}