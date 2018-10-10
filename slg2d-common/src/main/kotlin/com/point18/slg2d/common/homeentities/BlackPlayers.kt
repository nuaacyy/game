package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import javax.persistence.*

@NoArgConstructor
data class BlackPlayerPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val BLACK_PLAYER_QUERY_PLAYER = "blackPlayerByPlayerId"

@NamedQueries(
    NamedQuery(
        name = BLACK_PLAYER_QUERY_PLAYER,
        // language=HQL
        query = "from BlackPlayerEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "black_players")
@IdClass(FriendGroupPK::class)
data class BlackPlayerEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "world_id", nullable = false, length = 200)
    var worldId: Long,

    @Column(name = "black_player_id", nullable = false, length = 200)
    var blackPlayerId: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "photo_id", nullable = false)
    var photoId: Int,

    @Column(name = "fight_value", nullable = false)
    var fightValue: Int,

    @Column(name = "castle_lv", nullable = false)
    var castleLv: Int,

    @Column(name = "castle_skin", nullable = false)
    var castleSkin: Int,

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,

    @Column(name = "alliance_short_name", nullable = false)
    var allianceShortName: String,

    @Column(name = "short_name", nullable = false)
    var shortName: String,

    @Column(name = "player_id", nullable = false)
    override var playerId: Long      //
) : PlayerAsset {
    constructor() : this(0, 0, 0, "",0,0,0,0,0,0,"","",0)

    override fun primaryKey() = BlackPlayerPK(playerId, id)
}
