package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val CAVE_NAMED_QUERY = "findCaveByWorldId"

@NamedQueries(
    NamedQuery(
        name = CAVE_NAMED_QUERY,
        // language=HQL
        query = "from CaveEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "caves")
data class CaveEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,    // 玩家Id

    @Column(name = "hide_force_group_id", nullable = false)
    var hideForceGroupId: Long,    // 藏兵部队Id

    @Column(name = "hide_start_time", nullable = false)
    var hideStartTime: Long,  // 藏兵开始时间

    @Column(name = "hide_over_time", nullable = false)
    var hideOverTime: Long    // 藏兵结束时间
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}