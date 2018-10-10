package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val INSTANCE_NAMED_QUERY = "findInstanceByWorldId"

@NamedQueries(
    NamedQuery(
        name = INSTANCE_NAMED_QUERY,
        // language=HQL
        query = "from InstanceEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "instance")
data class InstanceEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "now_fight", nullable = false)
    var nowFight: Int,          // 排序ID

    @Column(name = "star_info", nullable = false, length = 5000)
    var starInfo: String,  // 预设名字

    @Column(name = "unit_infos", nullable = false, length = 5000)
    var unitInfos: String,  // 预设内容

    @Column(name = "player_id", nullable = false)
    var playerId: Long   // 玩家ID
) : WorldAsset {
    constructor() : this(0,0, 0, "", "", 0)

    override fun primaryKey() = id
}