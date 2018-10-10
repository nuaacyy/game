package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class IconPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val ICON_NAMED_QUERY_PLAYER = "findIconByPlayerId"

@NamedQueries(
    NamedQuery(
        name = ICON_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from IconEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "icons")
@IdClass(IconPK::class)
data class IconEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "icon_id", nullable = false)
    var iconId: Int,                                              // 头像模板ID

    @Column(name = "player_id", nullable = false)
    @Index(name = "ICONS_PLAYER_ID")
    override var playerId: Long,                                  // 拥有该头像的玩家ID

    @Column(name = "over_time", nullable = false)
    var overTime: Long                                       // 头像使用截至时间
) : PlayerAsset {

    constructor() : this(0, 0, 0, 0)

    override fun primaryKey() = IconPK(playerId, id)
}

