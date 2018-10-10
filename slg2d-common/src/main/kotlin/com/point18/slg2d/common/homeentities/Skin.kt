package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class SkinPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val SKIN_NAMED_QUERY = "findSkinByPlayerId"

@NamedQueries(
    NamedQuery(
        name = SKIN_NAMED_QUERY,
        // language=HQL
        query = "from SkinEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "skins")
@IdClass(SkinPK::class)
data class SkinEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "skin_type", nullable = false)
    var skinType: Int,  // 皮肤类型 1默认皮肤

    @Column(name = "star", nullable = false)
    var star: Int,  // 星数

    @Column(name = "is_use", nullable = false)
    var isUse: Int,

    @Column(name = "player_id", nullable = false)
    @Index(name = "SKINS_PLAYER_ID")
    override var playerId: Long    // 玩家ID
) : PlayerAsset {
    constructor() : this(0L, 0, 0, 0, 0L)

    override fun primaryKey() = SkinPK(playerId, id)
}

