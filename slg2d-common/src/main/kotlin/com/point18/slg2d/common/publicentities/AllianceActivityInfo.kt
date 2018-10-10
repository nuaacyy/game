package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.AllianceAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val EVERY_ALLIANCE_ACTIVITY_NAMED_QUERY = "findEveryAllianceActivity"

@NamedQueries(
    NamedQuery(
        name = EVERY_ALLIANCE_ACTIVITY_NAMED_QUERY,
        // language=HQL
        query = "from EveryAllianceActivityEntity where allianceId = :allianceId"
    )
)
@Entity
@Table(name = "every_alliance_activities")
data class EveryAllianceActivityEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "alliance_id", nullable = false)
    @Index(name = "EVERY_ALLIANCE_ACTIVITIES_ALLIANCE_ID")
    override var allianceId: Long,

    @Column(name = "activity_id", nullable = false)
    var activityId: Int,

    @Column(name = "score", nullable = false)
    var score: Int,

    @Column(name = "now_target", nullable = false)
    var nowTarget: Int

) : AllianceAsset {
    constructor() : this(0, 0, 0, 0, 0)

    override fun primaryKey() = id
}