package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val BELONG_CELL_NAMED_QUERY = "findBelongCellByWorldId"

@NamedQueries(
    NamedQuery(
        name = BELONG_CELL_NAMED_QUERY,
        // language=HQL
        query = "from BelongCellEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "belong_cell")
data class BelongCellEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "x", nullable = false)
    var x: Int,         // 所在坐标

    @Column(name = "y", nullable = false)
    var y: Int,         // 所在坐标

    @Column(name = "over_time", nullable = false)
    var overTime: Long,     // 结束时间

    @Column(name = "player_id", nullable = false)
    var playerId: Long,  // 归属玩家Id(防守方玩家Id)

    @Column(name = "def_player_id", nullable = false)
    var defPlayerId: Long,  // 防守方玩家Id

    @Column(name = "atk_battle_rs", nullable = false)
    var atkBattleRs: Int,

    @Column(name = "def_battle_rs", nullable = false)
    var defBattleRs: Int
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}