package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class InMakeKingEquipPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val IN_MAKE_KING_EQUIP_NAMED_QUERY = "findInMakeKingEquipByPlayerId"

@NamedQueries(
    NamedQuery(
        name = IN_MAKE_KING_EQUIP_NAMED_QUERY,
        // language=HQL
        query = "from InMakeKingEquipEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "in_make_king_equips")
@IdClass(InMakeKingEquipPK::class)
data class InMakeKingEquipEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "king_equip_id", nullable = false)
    var kingEquipId: Int,                // 要锻造的模版

    @Column(name = "hei_yao_id", nullable = false)
    var heiYaoId: Int,                // 放入的黑曜石模版ID

    @Column(name = "cost_equip_id", nullable = false)
    var costEquipId: Long,                // 放入的材料装备ID

    @Column(name = "over_time", nullable = false)
    var overTime: Long,  // 锻造结束时间

    @Column(name = "player_id", nullable = false)
    @Index(name = "IN_MAKE_KING_EQUIPS_PLAYER_ID")
    override var playerId: Long           // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun primaryKey() = InMakeKingEquipPK(playerId, id)
}
