package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val CHAT_NAMED_QUERY = "findChatByWorldId"

@NamedQueries(
    NamedQuery(
        name = CHAT_NAMED_QUERY,
        // language=HQL
        query = "from ChatEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "chats")
data class ChatEntity(

    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "msg", nullable = false, length = 500)
    var msg: String,

    @Column(name = "msg_type", nullable = false)
    var msgType: Int,

    @Column(name = "read_type", nullable = false)
    var readType: Int,    // 读取类型

    @Column(name = "vip_lv", nullable = false)
    var vipLv: Int,

    @Column(name = "icon_proto_id", nullable = false)
    var iconProtoId: Int,

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

    @Column(name = "chat_time", nullable = false)
    var chatTime: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 玩家ID
) : WorldAsset {

    constructor() : this(
        0, 0, "", 0, 0, 0, 0, 0, "", "",
        "", "", 0,
        0, 0, 0
    )

    override fun primaryKey() = id
}

