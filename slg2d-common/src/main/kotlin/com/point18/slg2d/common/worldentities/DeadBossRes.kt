package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val DEAD_BOSS_NAMED_QUERY = "findDeadBossByWorldId"

@NamedQueries(
    NamedQuery(
        name = DEAD_BOSS_NAMED_QUERY,
        // language=HQL
        query = "from DeadBossResEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "dead_boss_reses")
data class DeadBossResEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "res_id", nullable = false)
    var resId: Int,

    @Column(name = "x", nullable = false)
    var x: Int,

    @Column(name = "y", nullable = false)
    var y: Int,

    @Column(name = "over_time", nullable = false)
    var overTime: Long,

    @Column(name = "now_res_num", nullable = false)
    var nowResNum: Int,

    @Column(name = "group_id", nullable = false)
    var groupId: Long,   // 屯田部队ID

    @Column(name = "farm_start_time", nullable = false)
    var farmStartTime: Long,  //采集开始时间

    @Column(name = "farm_end_time", nullable = false)
    var farmEndTime: Long   // 采集结束时间
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}