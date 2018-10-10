package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val COMMON_BOSS_DATA_NAMED_QUERY = "findCommonBossDataByWorldId"

@NamedQueries(
    NamedQuery(
        name = COMMON_BOSS_DATA_NAMED_QUERY,
        // language=HQL
        query = "from CommonBossDataEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "common_bosses")
data class CommonBossDataEntity(
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
    @Column(name = "boss_datas", nullable = true)
    var bossDatas: String      // 魔物信息
) : WorldAsset {
    constructor() : this(0, 0, 0,  "")

    override fun primaryKey() = id
}