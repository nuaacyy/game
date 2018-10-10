package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val QUOTA_BAG_NAMED_QUERY = "findQuotaBagByPlayerId"

@NamedQueries(
    NamedQuery(
        name = QUOTA_BAG_NAMED_QUERY,
        // language=HQL
        query = "from QuotaBagEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "quota_bags")
data class QuotaBagEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,

    @Column(name = "quota_bag_id", nullable = false)
    var quotaBagId: Int, // 满额礼包ID

    @Column(name = "end_time", nullable = false)
    var endTime: Long, // 上一次礼包结束时间

    @Column(name = "degree", nullable = false)
    var degree: Int, // 触发礼包后充值的钻石数量

    @Column(name = "reward_id", nullable = false)
    var rewardId: Int // 礼包奖励
) : OneToOnePlayerAsset {

    constructor() : this(
        0, 0, 0, 0, 0
    )
}