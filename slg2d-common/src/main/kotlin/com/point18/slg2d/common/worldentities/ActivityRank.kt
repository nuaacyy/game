package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

// 活动历史排行数据
const val ACTIVITY_RANK_NAMED_QUERY = "findActivityRankByWorldId"

@NamedQueries(
    NamedQuery(
        name = ACTIVITY_RANK_NAMED_QUERY,
        // language=HQL
        query = "from ActivityRankEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "activity_ranks")
data class ActivityRankEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 结算时间

    @Column(name = "rank_nfo", nullable = false, length = 100000)
    var rankInfo: String     // 排行具体信息
) : WorldAsset {
    constructor() : this(0, 0, 0, "")

    override fun primaryKey() = id
}