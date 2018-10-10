package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val ALLIANCE_ACTIVITY_RANK_NAMED_QUERY = "findAllianceActivityRank"

@NamedQueries(
    NamedQuery(
        name = ALLIANCE_ACTIVITY_RANK_NAMED_QUERY,
        // language=HQL
        query = "from AllianceActivityRankEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "alliance_activity_ranks")
data class AllianceActivityRankEntity(
    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
     var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "ALLIANCE_ACTIVITY_RANKS_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 充值的时间

    @Column(name = "rank_info", nullable = false, length = 100000)
    var rankInfo: String            // 玩家ID

) : PublicAsset {
    constructor() : this(0, 0,0, "")

    override fun primaryKey() = id
}