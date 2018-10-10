package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val PUBLIC_ACTIVITY_NAMED_QUERY = "findPublicActivity"

@NamedQueries(
    NamedQuery(
        name = PUBLIC_ACTIVITY_NAMED_QUERY,
        // language=HQL
        query = "from PublicActivityEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "public_activities")
data class PublicActivityEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "PUBLIC_ACTIVITIES_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "activity_id", nullable = false)
    var activityId: Int,

    @Column(name = "now_state", nullable = false)
    var nowState: Int,

    @Column(name = "reward_time", nullable = false)
    var rewardTime: Long,

    @Column(name = "over_time", nullable = false)
    var overTime: Long

) : PublicAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = id
}