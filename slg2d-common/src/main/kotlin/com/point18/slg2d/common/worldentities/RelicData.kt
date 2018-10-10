package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val RELIC_DATA_NAMED_QUERY = "findRelicDataByWorldId"

@NamedQueries(
    NamedQuery(
        name = RELIC_DATA_NAMED_QUERY,
        // language=HQL
        query = "from RelicDataEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "relices")
data class RelicDataEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "grid_id", nullable = false)
    var gridId: Int,

    @Type(type = "text")
    @Column(name = "relic_datas", nullable = true)
    var relicDatas: String      // 魔物信息
) : WorldAsset {
    constructor() : this(0, 0, 0, "")

    override fun primaryKey() = id
}