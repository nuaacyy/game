package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class FriendApplyPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FRIEND_APPLY_NAMED_QUERY = "findFriendApplyByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FRIEND_APPLY_NAMED_QUERY,
        // language=HQL
        query = "from FriendApplyEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "friend_applys")
@IdClass(FriendApplyPK::class)
data class FriendApplyEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "world_id", nullable = false)
    var worldId: Long,                   // 使用的方案

    @Column(name = "apply_player_id", nullable = false)
    var applyPlayerId: Long,                                  // 加我为好友的玩家ID

    @Column(name = "apply_player_name", nullable = false, length = 50)
    var applyPlayerName: String,                                      // 加我为好友的玩家名

    @Column(name = "apply_player_photo_id", nullable = false)
    var applyPlayerPhotoId: Int,                                      // 加我为好友的玩家头像

    @Column(name = "apply_player_area_no", nullable = false)
    var applyPlayerAreaNo: Int,                                              // 加我为好友的玩家所在服务器编号

    @Column(name = "apply_player_vip_lv", nullable = false)
    var applyPlayerVipLv: Int,                                              // 加我为好友的玩家VIP等级

    @Column(name = "apply_player_alliance_short_name", nullable = false, length = 50)
    var applyPlayerAllianceShortName: String,                                      // 加我为好友的玩家联盟简称

    @Column(name = "apply_state", nullable = false)
    var applyState: Int,                                              // 玩家的处理状态 0-未处理 1-已接受 2-已拒绝

    @Column(name = "apply_time", nullable = false)
    var applyTime: Long,  // 数据生成时间

    @Column(name = "apply_castle_lev", nullable = false)
    var applyCastleLv: Int,                                              // 玩家的处理状态 0-未处理 1-已接受 2-已拒绝

    @Column(name = "apply_castle_skin", nullable = false)
    var applyCastleSkin: Int,  // 数据生成时间

    @Column(name = "apply_player_short_name", nullable = false)
    var applyPlayerShortName: String,  // 数据生成时间

    @Column(name = "player_id", nullable = false)
    @Index(name = "FRIEND_APPLYS_PLAYER_ID")
    override var playerId: Long    // 玩家ID
) : PlayerAsset {
    constructor() : this(0, 0, 0, "", 0, 0, 0, "", 0, 0, 0, 0, "", 0)

    override fun primaryKey() = FriendApplyPK(playerId, id)
}