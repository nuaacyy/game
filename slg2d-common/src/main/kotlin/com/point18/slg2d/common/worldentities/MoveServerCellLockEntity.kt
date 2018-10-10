package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val MOVE_SERVER_CELL_LOCK_QUERY = "findMoveServerCellLockByWorldId"

@NamedQueries(
    NamedQuery(
        name = MOVE_SERVER_CELL_LOCK_QUERY,
        // language=HQL
        query = "from MoveServerCellLockEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "move_server_cell_lock")
data class MoveServerCellLockEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "x", nullable = false)
    var x: Int,

    @Column(name = "y", nullable = false)
    var y: Int,

    @Column(name = "start_time", nullable = false)
    var startTime: Long, // 锁定时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long  // 操作玩家ID

) : WorldAsset {
    constructor() : this(0L, 0L, 0, 0, 0L, 0L)

    override fun primaryKey() = id
}