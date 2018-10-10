package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import org.hibernate.annotations.Index
import xyz.ariane.util.annotation.NoArgConstructor
import java.io.Serializable
import javax.persistence.*

@NoArgConstructor
data class GiftBagPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val GIFT_BAG_NAMED_QUERY = "findGiftBagByPlayerId"

@NamedQueries(
    NamedQuery(
        name = GIFT_BAG_NAMED_QUERY,
        // language=HQL
        query = "from GiftBagEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "gift_bags")
@IdClass(GiftBagPK::class)
data class GiftBagEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "player_id", nullable = false)
    @Index(name = "GIFTBAGS_PLAYER_ID")
    override var playerId: Long,                                  // 玩家编号

    @Column(name = "gift_id", nullable = false)
    var giftId: Int,                                              // 礼包id

    @Column(name = "end_time", nullable = false)
    var endTime: Long,                                              // 礼包结束时间

    @Column(name = "cur_level", nullable = false)
    var curLevel: Int,                                    // 当前档位

    @Column(name = "cur_count", nullable = false)
    var curCount: Int                                    // 当前档位充值次数
) : PlayerAsset {

    constructor() : this(
        0, 0, 0, 0, 0, 0
    )

    override fun primaryKey(): Serializable = GiftBagPK(playerId, id)
}