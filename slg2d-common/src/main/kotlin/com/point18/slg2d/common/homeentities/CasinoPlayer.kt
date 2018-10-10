package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class CasinoPlayerPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val CASINO_PLAYER_ID_QUERY = "findCasinoInfoByPlayerId"

@NamedQueries(
    NamedQuery(
        name = CASINO_PLAYER_ID_QUERY,
        // language=HQL
        query = "from CasinoPlayerEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "casino_player")
@IdClass(CasinoPlayerPK::class)
data class CasinoPlayerEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "palace_id", nullable = false)
    var palaceId: Int,

    @Column(name = "finish_date", nullable = false)
    var finishDate: Long,  // 下次刷新时间

    @Column(name = "player_id", nullable = false)
    @Index(name = "CASINO_PLAYER_PLAYER_ID")
    override var playerId: Long         // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0L, 0)

    override fun primaryKey() = CasinoPlayerPK(playerId, id)
}
