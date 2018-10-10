package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val BUFF_NAMED_QUERY = "findBuffByWorldId"

@NamedQueries(
    NamedQuery(
        name = BUFF_NAMED_QUERY,
        // language=HQL
        query = "from BuffEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "buffs")
data class BuffEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "proto_id", nullable = false)
    var protoId: Int,                                              // buff模板ID

    @Column(name = "player_id", nullable = false)
    var playerId: Long,                                  // 玩家ID

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 过期时间

    @Column(name = "start_time", nullable = false)
    var startTime: Long    // 开始时间
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}