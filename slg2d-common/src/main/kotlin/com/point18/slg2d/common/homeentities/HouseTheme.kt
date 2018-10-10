package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class HouseThemePK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val HOUSE_THEME_NAMED_QUERY_PLAYER = "findHouseThemeByPlayerId"

@NamedQueries(
    NamedQuery(
        name = HOUSE_THEME_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from HouseThemeEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "house_theme")
@IdClass(HouseThemePK::class)
data class HouseThemeEntity(
    // 主题唯一ID
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "theme_name", nullable = false, length = 100)
    var themeName: String,  // 主题名称

    @Column(name = "template", nullable = false, length = 2000)
    var template: String,  // 主题模板(家具信息)

    @Column(name = "", nullable = false)
    var subjectId: Int,  // 标识是否是商店主题

    @Column(name = "player_id", nullable = false)
    @Index(name = "HOUSE_THEME_PLAYER_ID")
    override var playerId: Long    // 所属玩家Id
) : PlayerAsset {
    constructor() : this(
        0, "", "", 0, 0
    )

    override fun primaryKey() = HouseThemePK(playerId, id)
}