package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import javax.persistence.*

@NoArgConstructor
data class BagPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val BAG_NAMED_QUERY = "findBagByPlayerId"

@NamedQueries(
    NamedQuery(
        name = BAG_NAMED_QUERY,
        // language=HQL
        query = "from BagEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "bags")
@IdClass(BagPK::class)
data class BagEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "bag_type", nullable = false)
    var bagType: Int,  // 背包类型  1-普通背包 2-武将身上佩戴背包 3-君主身上佩戴背包

    @Column(name = "player_id", nullable = false)
    override var playerId: Long
) : PlayerAsset {
    constructor() : this(0, 0, 0)

    override fun primaryKey() = BagPK(playerId, id)
}