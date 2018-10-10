package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import javax.persistence.*

@NoArgConstructor
data class CasinoPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val CASINO_ID_QUERY = "findCasinoByPlayerId"

@NamedQueries(
    NamedQuery(
        name = CASINO_ID_QUERY,
        // language=HQL
        query = "from CasinoEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "casinos")
@IdClass(CasinoPK::class)
data class CasinoEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "palace_id", nullable = false)
    var palaceId: Int,

    @Column(name = "palace_level", nullable = false)
    var palaceLevel: Int,  // 类型

    @Column(name = "is_bless", nullable = false)
    var isBless: Int,  // 是否是祝福

    @Column(name = "bless_count", nullable = false)
    var blessCount: Int,  // 祝福次数

    @Column(name = "use_bless_count", nullable = false)
    var useBlessCount: Int,  // 祝福次数

    @Column(name = "atk_count", nullable = false)
    var atkCount: Int,  // 打小怪次数

    @Column(name = "atk_boss_count", nullable = false)
    var atkBossCount: Int, // 打boss次数

    @Column(name = "is_atk_boss", nullable = false)
    var isAtkBoss: Int,  // 是否在攻击boss

    @Column(name = "total_atk_boss_count", nullable = false)
    var totalAtkBossCount: Int,  // boss可攻击次数

    @Column(name = "is_atk", nullable = false)
    var isAtk: Int,

    @Column(name = "free_count", nullable = false)
    var freeCount: Int,

    @Column(name = "open_free_Time", nullable = false)
    var openFreeTime: Long,

    @Column(name = "player_id", nullable = false)
    override var playerId: Long         // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, 0, 0,0,0, 0,0,0,0,0,0)

    override fun primaryKey() = CasinoPK(playerId, id)
}
