package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import org.hibernate.annotations.Type
import javax.persistence.*

const val LIBRARY_NAMED_QUERY_PLAYER = "findLibraryByPlayerId"

@NamedQueries(
    NamedQuery(
        name = LIBRARY_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from LibraryEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "library")
data class LibraryEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Type(type = "text")
    @Column(name = "prop", nullable = false)
    var prop: String,    // 道具

    @Type(type = "text")
    @Column(name = "equip", nullable = false)
    var equip: String,    // 装备

    @Type(type = "text")
    @Column(name = "card", nullable = false)
    var card: String,    // 卡片

    @Type(type = "text")
    @Column(name = "boss", nullable = false)
    var boss: String,    // 魔物

    @Type(type = "text")
    @Column(name = "monster", nullable = false)
    var monster: String,    // 怪物

    @Column(name = "new_item", nullable = false, length = 200)
    var newItem: String    // 有新图鉴

) : OneToOnePlayerAsset {
    constructor() : this(
        0, "", "", "", "", "", ""
    )
}