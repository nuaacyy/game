package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val CASTLE_NAMED_QUERY = "findCastleByWorldId"

@NamedQueries(
    NamedQuery(
        name = CASTLE_NAMED_QUERY,
        // language=HQL
        query = "from CastleEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "castles")
data class CastleEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "db_id", nullable = false)
    var dbId: Long,

    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "name", nullable = false, length = 100)
    var name: String,              // 城池名

    @Column(name = "proto_id", nullable = false)
    var protoId: Int,              // 城池模板ID

    @Column(name = "lv", nullable = false)
    var lv: Int,              // 城池等级

    @Column(name = "x", nullable = false)
    var x: Int,              // 城池x坐标

    @Column(name = "y", nullable = false)
    var y: Int,              // 城池y坐标

    @Column(name = "type", nullable = false)
    var type: Int,              // 城池类型

    @Column(name = "castle_state", nullable = false)
    var castleState: Int,              // 城池状态  随便写写的 具体功能还没做  实现机器人而已 0-和平 3-冒烟 4-冒火

    @Column(name = "castle_status_end_time", nullable = false)
    var castleStatusEndTime: Long, // 城堡状态结束时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long  // 玩家id

) : WorldAsset {
    constructor() : this(0, 0, 0,  "", 0, 0, 0, 0, 0, 0, 0, 0)

    override fun primaryKey() = dbId
}