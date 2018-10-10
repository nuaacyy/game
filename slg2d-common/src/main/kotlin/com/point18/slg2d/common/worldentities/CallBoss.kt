package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val CALL_BOSS_NAMED_QUERY = "findCellBossByWorldId"

@NamedQueries(
    NamedQuery(
        name = CALL_BOSS_NAMED_QUERY,
        // language=HQL
        query = "from CallBossEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "call_bosses")
data class CallBossEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "boss_id", nullable = false)
    var bossId: Int,   // bossId

    @Column(name = "x", nullable = false)
    var x: Int,    // 所在坐标

    @Column(name = "y", nullable = false)
    var y: Int,    // 所在坐标

    @Column(name = "protect_over_time", nullable = false)
    var protectOverTime: Long,  // 保护消失时间

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 消失时间

    @Column(name = "now_hp", nullable = false)
    var nowHp: Int,                // 当前血量

    @Type(type = "text")
    @Column(name = "atk_records", nullable = true)
    var atkRecords: String,      // 玩家进攻记录

    @Column(name = "player_id", nullable = false)
    var playerId: Long,    // 玩家ID

    @Column(name = "alliance_id", nullable = false)
    var allianceId: Long,    // 联盟ID

    @Column(name = "help_members", nullable = false, length = 5000)
    var helpMembers: String

) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, "", 0, 0, "")

    override fun primaryKey() = id
}