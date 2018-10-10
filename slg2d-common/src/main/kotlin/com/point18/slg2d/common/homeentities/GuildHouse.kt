package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val GUILD_HOUSE_NAMED_QUERY_PLAYER = "findGuildHouseByPlayerId"

@NamedQueries(
    NamedQuery(
        name = GUILD_HOUSE_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from GuildHouseEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "guild_house")
data class GuildHouseEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "name", nullable = false, length = 100)
    var name: String,    // 后宅名称

    @Column(name = "comfort", nullable = false)
    var comfort: Int    // 舒适度
) : OneToOnePlayerAsset {
    constructor() : this(
        0,"", 0
    )
}

