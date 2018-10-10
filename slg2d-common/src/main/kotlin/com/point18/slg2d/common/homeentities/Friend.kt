package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class FriendPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FRIEND_NAMED_QUERY = "findFriendByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FRIEND_NAMED_QUERY,
        // language=HQL
        query = "from FriendEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "friends")
@IdClass(FriendPK::class)
data class FriendEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "world_id", nullable = false)
    var worldId: Long,

    @Column(name = "group_id", nullable = false)
    var groupId: Long,

    @Column(name = "tar_player_id", nullable = false)
    var tarPlayerId: Long,

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

    @Column(name = "is_real_friend", nullable = false)
    var isRealFriend: Int,

    @Column(name = "short_name", nullable = false)
    var shortName: String,

    @Column(name = "player_id", nullable = false)
    @Index(name = "FRIENDS_PLAYER_ID")
    override var playerId: Long         // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, "",0,0,0,0,0,0,"",0, "",0)

    override fun primaryKey() = FriendPK(playerId, id)
}
