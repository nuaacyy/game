package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val WALK_FORCE_NAMED_QUERY = "findWalkForceByWorldId"

@NamedQueries(
    NamedQuery(
        name = WALK_FORCE_NAMED_QUERY,
        // language=HQL
        query = "from WalkForceEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "walk_forces")
data class WalkForceEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "hero_ids", nullable = false, length = 200)
    var heroIds: String,  // 武将IDS

    @Column(name = "soliders", nullable = false, length = 2000)
    var soliders: String,  // 携带士兵S

    @Column(name = "initial_soliders", nullable = false, length = 2000)
    var initialSoliders: String,  // 初始携带士兵，用于计算行军速度

    @Column(name = "res_from_info", nullable = false, length = 100)
    var resFromInfo: String,   // 资源来源，掠夺/采集/运输

    @Column(name = "res", nullable = false, length = 1000)
    var res: String,  // 出门携带的资源量,用于运输行军玩法

    @Column(name = "force_group_id", nullable = false)
    var forceGroupId: Long,  // 隶属于哪个行军主体

    @Column(name = "player_id", nullable = false)
    var playerId: Long  // 部队主人
) : WorldAsset {
    constructor() : this(0,
        0, "", "", "", "", "", 0, 0
    )

    override fun primaryKey() = id
}
