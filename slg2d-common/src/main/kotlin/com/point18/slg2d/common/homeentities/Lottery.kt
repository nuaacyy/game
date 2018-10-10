package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class LotteryPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val LOTTERY_NAMED_QUERY_PLAYER = "findLotteryByPlayerId"

@NamedQueries(
    NamedQuery(
        name = LOTTERY_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from LotteryEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "lotterys")
@IdClass(LotteryPK::class)
data class LotteryEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "proto_Id", nullable = false)
    var protoId: Int,

    @Column(name = "discount_today", nullable = false)
    var discountToday: Int,

    @Column(name = "activity_over_time", nullable = false)
    var activityOverTime: Long,

    @Column(name = "player_id", nullable = false)
    @Index(name = "LOTTERYS_PLAYER_ID")
    override var playerId: Long,

    @Column(name = "rest_free_draw_times", nullable = false)
    var restFreeDrawTimes: Int,

    @Column(name = "this_round_draw_sum", nullable = false)
    var thisRoundDrawSum: Long,

    @Column(name = "draw_sum", nullable = false)
    var drawSum: Long,

    @Column(name = "last_use_discount_time", nullable = false)
    var lastUseDiscountTime: Long,

    @Column(name = "free_refresh_time", nullable = false)
    var freeRefreshTime: Long,

    @Column(name = "last_free_draw_time", nullable = false)
    var lastFreeDrawTime: Long

) : PlayerAsset {
    constructor() : this(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0
    )

    override fun primaryKey() = LotteryPK(playerId, id)
}