package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val VIP_NAMED_QUERY = "findVipByPlayerId"

@NamedQueries(
    NamedQuery(
        name = VIP_NAMED_QUERY,
        // language=HQL
        query = "from VipEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "vips")
data class VipEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long, // 玩家Id

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,                                              // VIP等级

    @Column(nullable = false)
    var vipExp: Int,                                              // vip经验

    @Column(name = "last_get_vip_reward_time", nullable = false)
    var lastGetVipRewardTime: Long,  // 上次领取vip奖励时间

    @Column(name = "continue_online_day", nullable = false)
    var continueOnlineDay: Int,                                              // 连续上线天数)

    @Column(name = "last_refresh_energy_time", nullable = false)
    val lastRefreshEnergyTime: Long

) : OneToOnePlayerAsset {

    constructor() : this(0, 0, 0, 0, 0, 0)
}

