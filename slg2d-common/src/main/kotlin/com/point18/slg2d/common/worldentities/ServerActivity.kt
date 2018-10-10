package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val SERVER_ACTIVITY_NAMED_QUERY = "findServerActivityByWorldId"

@NamedQueries(
    NamedQuery(
        name = SERVER_ACTIVITY_NAMED_QUERY,
        // language=HQL
        query = "from ServerActivityEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "server_activities")
data class ServerActivityEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "activity_id", nullable = false)
    var activityId: Int,                // 活动模板ID

    @Column(name = "over_time", nullable = false)
    var overTime: Long  // 结束时间
) : WorldAsset {
    constructor() : this(0,0L, 0, 0L)

    override fun primaryKey() = id
}