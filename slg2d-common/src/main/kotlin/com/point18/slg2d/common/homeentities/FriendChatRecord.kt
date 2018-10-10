package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class FriendChatRecordPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val FRIENDCHATRECORD_NAMED_QUERY_PLAYER = "findFriendChatRecordByPlayerId"

@NamedQueries(
    NamedQuery(
        name = FRIENDCHATRECORD_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from FriendChatRecordEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "friend_chat")
@IdClass(FriendChatRecordPK::class)
data class FriendChatRecordEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "friend_id", nullable = false)
    var friendId: Long,                          // 聊天的对象(不能是自己)

    @Column(name = "record", nullable = false,length = 1000)
    var record: String,

    @Column(name = "icon_id", nullable = false)
    var iconId: Int,

    @Column(name = "last_talk_time", nullable = false)
    var lastTalkTime: Long,

    @Column(name = "msg_type", nullable = false)
    var msgType: Int,

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,

    @Column(name = "alliance_pos", nullable = false)
    var alliancePos: Int,

    @Column(name = "alliance_name", nullable = false, length = 50)
    var allianceName: String,

    @Column(name = "alliance_short_name", nullable = false, length = 50)
    var allianceShortName: String,

    @Column(name = "player_name", nullable = false, length = 50)
    var playerName: String,

    @Column(name = "player_short_name", nullable = false, length = 50)
    var playerShortName: String,

    @Column(name = "kingdom_pos", nullable = false)
    var kingdomPos: Int,

    @Column(name = "wonder_pos", nullable = false)
    var wonderPos: Int,

    @Column(name = "talk_player_id", nullable = false)
    var talkPlayerId: Long,                       // 说出这句话的人,可以是自己,可以是别人

    @Column(name = "area_no", nullable = false)
    var areaNo: Int,

    @Column(name = "player_id", nullable = false)
    @Index(name = "FRIEND_CHAT_PLAYER_ID")
    override var playerId: Long

) : PlayerAsset {

    constructor() : this(0, 0, "", 0, 0, 0, 0, 0,
        "", "", "", "", 0, 0,
        0,0,0)

    override fun primaryKey() = FriendChatRecordPK(playerId, id)
}