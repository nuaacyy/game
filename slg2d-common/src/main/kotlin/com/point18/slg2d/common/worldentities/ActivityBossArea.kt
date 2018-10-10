package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val ACTIVITY_BOSS_AREA_NAMED_QUERY = "findActivityBossAreaByWorldId"

@NamedQueries(
    NamedQuery(
        name = ACTIVITY_BOSS_AREA_NAMED_QUERY,
        // language=HQL
        query = "from ActivityBossAreaEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "activity_boss_area")
data class ActivityBossAreaEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "location_id", nullable = false)
    var locationId: Int,

    @Column(name = "activity_boss_id", nullable = false)
    var activityBossId: Int,

    @Column(name = "start_time", nullable = false)
    var startTime: Long,  // 开始时间

    @Column(name = "refresh_time", nullable = false)
    var refreshTime: Long,  // 击杀后下个boss的刷新时间

    @Column(name = "finish_time", nullable = false)
    var finishTime: Long,  // 结束时间

    @Column(name = "status", nullable = false)
    var status: Int,                // boss状态

    @Column(name = "advance_mail", nullable = false)
    var advanceMail: Int,                // boss状态

    @Column(name = "init_time", nullable = false)
    var initTime: Long

) : WorldAsset {
    constructor() : this(
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}