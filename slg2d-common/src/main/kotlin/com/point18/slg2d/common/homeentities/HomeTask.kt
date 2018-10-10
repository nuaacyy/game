package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class HomeTaskPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val HOME_TASK_NAMED_QUERY = "findHomeTaskByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOME_TASK_NAMED_QUERY,
        // language=HQL
        query = "from HomeTaskEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "home_tasks")
@IdClass(HomeTaskPK::class)
data class HomeTaskEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
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

    @Column(name = "on_world", nullable = false)
    var onWorld: Int,              // 是否正在世界进行

    @Column(name = "player_id", nullable = false)
    @Index(name = "HOME_TASKS_PLAYER_ID")
    override var playerId: Long    //外键玩家ID
) : PlayerAsset {
    constructor() : this(0L, 0, 0, 0, 0L, 0, 0L)

    override fun primaryKey() = HomeTaskPK(playerId, id)
}