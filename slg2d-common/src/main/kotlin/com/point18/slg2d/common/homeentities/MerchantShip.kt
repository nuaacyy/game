package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val MERCHANT_SHIP_NAMED_QUERY_PLAYER = "findMerchantShipByPlayerId"

@NamedQueries(
    NamedQuery(
        name = MERCHANT_SHIP_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from MerchantShipEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "merchant_ships")
data class MerchantShipEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "next_re_time", nullable = false)
    var nextReTime: Long,  // 下次刷新时间

    @Column(name = "now_times", nullable = false)
    var nowTimes: Int,

    @Column(name = "shop", nullable = false, length = 2000)
    var shop: String     // 物品信息

) : OneToOnePlayerAsset {
    constructor() : this(0, 0, 0, "")
}

