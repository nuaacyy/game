package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val COMMON_RES_DATA_NAMED_QUERY = "findCommonResDataByWorldId"

@NamedQueries(
    NamedQuery(
        name = COMMON_RES_DATA_NAMED_QUERY,
        // language=HQL
        query = "from CommonResDataEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "common_reses")
data class CommonResDataEntity(
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
    @Column(name = "res_datas", nullable = true)
    var resDatas: String
) : WorldAsset {
    constructor() : this(0, 0, 0, "")

    override fun primaryKey() = id
}