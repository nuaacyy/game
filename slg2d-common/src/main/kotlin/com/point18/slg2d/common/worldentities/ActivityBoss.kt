package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val ACTIVITY_BOSS_NAMED_QUERY = "findActivityBossByWorldId"

@NamedQueries(
    NamedQuery(
        name = ACTIVITY_BOSS_NAMED_QUERY,
        // language=HQL
        query = "from ActivityBossEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "activity_bosses")
data class ActivityBossEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "boss_id", nullable = false)
    var bossId: Int,    // bossId

    @Column(name = "activity_boss_id", nullable = false)
    var activityBossId: Int,    // activityBossId

    @Column(name = "x", nullable = false)
    var x: Int,         // 所在坐标

    @Column(name = "y", nullable = false)
    var y: Int,         // 所在坐标

    @Column(name = "now_hp", nullable = false)
    var nowHp: Int,     // 当前血量

    @Type(type = "text")
    @Column(name = "atk_records", nullable = true)
    var atkRecords: String      // 玩家进攻记录
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, "")

    override fun primaryKey() = id
}