package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val BARRACKS_NAMED_QUERY = "findBarracksByWorldId"

@NamedQueries(
    NamedQuery(
        name = BARRACKS_NAMED_QUERY,
        // language=HQL
        query = "from BarracksEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "barracks")
data class BarracksEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "soldier_id", nullable = false)
    var soldierId: Int,                // 士兵模板ID

    @Column(name = "soldier_num", nullable = false)
    var soldierNum: Int,                // 士兵数量

    @Column(name = "start_time", nullable = false)
    var startTime: Long,  // 造兵开始时间

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 造兵结束时间

    @Column(name = "now_Make_num", nullable = false)
    var nowMakeNum: Int,                // 正在造的士兵数量

    @Column(name = "can_cure_num", nullable = false)
    var canCureNum: Int,                // 当前可治疗的士兵数量

    @Column(name = "now_cure_num", nullable = false)
    var nowCureNum: Int,                // 当前正在治疗的士兵数量

    @Column(name = "cure_start_time", nullable = false)
    var cureStartOverTime: Long,             // 治疗开始时间

    @Column(name = "cure_over_time", nullable = false)
    var cureOverTime: Long,             // 治疗结束时间

    @Column(name = "cure_queue", nullable = false)
    var cureQueue: Int,                 // 所属治疗队列

    @Column(name = "can_event_cure_num", nullable = false)
    var canEventCureNum: Int,                // 当前可治疗(活动)的士兵数量

    @Column(name = "now_event_cure_num", nullable = false)
    var nowEventCureNum: Int,                // 当前正在治疗(活动)的士兵数量

    @Column(name = "event_cure_start_time", nullable = false)
    var eventCureStartTime: Long,             // 治疗(活动)开始时间

    @Column(name = "event_cure_over_time", nullable = false)
    var eventCureOverTime: Long,             // 治疗(活动)结束时间

    @Column(name = "event_cure_queue", nullable = false)
    var eventCureQueue: Int,                 // 所属活动治疗队列

    @Column(name = "up_num", nullable = false)
    var upNum: Int,                                              // 当前正在晋升的数量

    @Column(name = "up_to_solider_id", nullable = false)
    var upToSoliderId: Int,                                              // 正在晋升到的兵种ID

    @Column(name = "up_start_time", nullable = false)
    var upStartTime: Long,  // 晋升开始时间

    @Column(name = "up_over_time", nullable = false)
    var upOverTime: Long,  // 晋升结束时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID
) : WorldAsset {
    constructor() : this(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}