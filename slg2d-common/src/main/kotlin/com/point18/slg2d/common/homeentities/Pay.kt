package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class PayPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val PAY_NAMED_QUERY_PLAYER = "findPayByPlayerId"

@NamedQueries(
    NamedQuery(
        name = PAY_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from PayEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "pays")
@IdClass(PayPK::class)
data class PayEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "pay_id", nullable = false)
    var payId: Int,               // pay.xml ID

    @Column(name = "pay_time", nullable = false)
    var payTime: Int,  // 充值的时间

    @Column(name = "player_id", nullable = false)
    @Index(name = "PAYS_PLAYER_ID")
    override var playerId: Long            // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0)

    override fun primaryKey() = PayPK(playerId, id)
}

