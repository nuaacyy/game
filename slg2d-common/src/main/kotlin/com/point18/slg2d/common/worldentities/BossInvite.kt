package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val BOSS_INVITE_NAMED_QUERY = "findBossInvite"

@NamedQueries(
    NamedQuery(
        name = BOSS_INVITE_NAMED_QUERY,
        // language=HQL
        query = "from BossInviteEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "boss_invites")
data class BossInviteEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "pos_x", nullable = false)
    var posX: Int,

    @Column(name = "pos_y", nullable = false)
    var posY: Int,

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long,

    @Column(name = "latest_invite_time", nullable = false)
    var latestInviteTime: Long
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}