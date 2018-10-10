package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val TASK_NAMED_QUERY = "findTaskByWorldId"

@NamedQueries(
    NamedQuery(
        name = TASK_NAMED_QUERY,
        // language=HQL
        query = "from TaskEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "tasks")
data class TaskEntity(
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

    @Column(name = "task_proto_id", nullable = false)
    var taskProtoId: Int,              //任务模板ID

    @Column(name = "task_now_state", nullable = false)
    var taskNowState: Int,              //当前状态 0-进行中 1-已完成 2-已领取奖励

    @Column(name = "task_finish", nullable = false)
    var taskFinish: Long,              //当前完成度

    @Column(name = "over_time", nullable = false)
    var overTime: Long,              // 任务结束时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long    //外键玩家ID
) : WorldAsset {
    constructor() : this(0L, 0, 0L, 0, 0, 0, 0L, 0L)

    override fun primaryKey() = dbId
}