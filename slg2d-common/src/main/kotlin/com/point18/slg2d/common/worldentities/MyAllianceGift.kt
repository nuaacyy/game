package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val MY_ALLIANCE_GIFT_NAMED_QUERY = "findMyAllianceGiftByWorldId"

@NamedQueries(
    NamedQuery(
        name = MY_ALLIANCE_GIFT_NAMED_QUERY,
        // language=HQL
        query = "from MyAllianceGiftEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "my_alliance_gifts")
data class MyAllianceGiftEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 过期时间

    @Column(name = "gift_id", nullable = false)
    var giftId: Int,                // 礼物表ID

    @Column(name = "is_get", nullable = false)
    var isGet: Int,                // 是否已领取  0-否 1-是

    @Column(name = "gift_info", nullable = false, length = 500)
    var giftInfo: String,      // 礼物内奖励

    @Column(name = "player_id", nullable = false)
    var playerId: Long           // 玩家ID
) : WorldAsset {
    constructor() : this(0, 0, 0, 0, 0, "", 0)

    override fun primaryKey() = id
}