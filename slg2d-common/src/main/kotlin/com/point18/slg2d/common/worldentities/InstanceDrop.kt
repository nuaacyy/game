package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val INSTANCE_DROP_NAMED_QUERY = "findInstanceDropByWorldId"

@NamedQueries(
    NamedQuery(
        name = INSTANCE_DROP_NAMED_QUERY,
        // language=HQL
        query = "from InstanceDropEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "instance_drop")
data class InstanceDropEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "props_id", nullable = false)
    var propsId: Int,

    @Column(name = "now_get", nullable = false)
    var nowGet: Int,

    @Column(name = "now_check_time", nullable = false)
    var nowCheckTime: Int,

    @Column(name = "player_id", nullable = false)
    var playerId: Long   // 玩家ID

) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}