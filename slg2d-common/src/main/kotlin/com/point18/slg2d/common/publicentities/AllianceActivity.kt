package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_ACTIVITY_NAMED_QUERY = "findAllianceActivity"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_ACTIVITY_NAMED_QUERY,
        // language=HQL
        query = "from AllianceActivityEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "alliance_activities")
data class AllianceActivityEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "ALLIANCE_ACTIVITIES_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "activity_id", nullable = false)
    var activityId: Int,

    @Column(name = "over_time", nullable = false)
    var overTime: Long
) : PublicAsset {
    constructor() : this(0, 0, 0, 0)

    override fun primaryKey() = id
}