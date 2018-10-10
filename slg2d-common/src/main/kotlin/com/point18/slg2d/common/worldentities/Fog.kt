package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val FOG_NAMED_QUERY = "findFogByWorldId"

@NamedQueries(
    NamedQuery(
        name = FOG_NAMED_QUERY,
        // language=HQL
        query = "from FogEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "fogs")
data class FogEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long,

    @Column(name = "fog_id", nullable = false)
    var fogId: Int,

    @Column(name = "state", nullable = false)
    var state: Int,

    @Column(name = "solider_info", nullable = false, length = 2000)
    var soliderInfo: String

) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, "")

    override fun primaryKey() = id
}