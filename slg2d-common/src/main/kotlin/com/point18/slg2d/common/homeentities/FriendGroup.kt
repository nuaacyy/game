package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class FriendGroupPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FRIEND_GROUP_NAMED_QUERY_PLAYER = "findFriendGroupByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FRIEND_GROUP_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from FriendGroupEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "friend_groups")
@IdClass(FriendGroupPK::class)
data class FriendGroupEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "group_name", nullable = false, length = 200)
    var groupName: String,    // 分组名称

    @Column(name = "player_id", nullable = false)
    @Index(name = "FRIEND_GROUPS_PLAYER_ID")
    override var playerId: Long      //
) : PlayerAsset {
    constructor() : this(0, "", 0)

    override fun primaryKey() = FriendGroupPK(playerId, id)
}
