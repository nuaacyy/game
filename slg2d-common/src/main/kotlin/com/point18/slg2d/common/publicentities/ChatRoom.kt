package com.point18.slg2d.common.publicentities

import com.point18.slg2d.common.persistence.PublicAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Index
import javax.persistence.*

const val CHATROOM_NAMED_QUERY = "findChatRoom"

@NamedQueries(
    NamedQuery(
        name = CHATROOM_NAMED_QUERY,
        // language=HQL
        query = "from ChatRoomEntity where publicId = :publicId"
    )
)
@Entity
@Table(name = "chat_rooms")
data class ChatRoomEntity(

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Id
    @Column(name = "public_id", nullable = false)
    @Index(name = "CHAT_ROOMS_PUBLIC_ID")
    override var publicId: Long,

    @Column(name = "room_name", nullable = false, length = 100)
    var roomName: String,

    @Column(name = "icon_proto_ids", nullable = false, length = 100)
    var iconProtoIds: String,

    @Column(name = "members", nullable = false, length = 5000)
    var members: String,

    @Column(name = "member_num", nullable = false)
    var memberNum: Int,

    @Column(name = "create_time", nullable = false)
    var createTime: Long,

    @Column(name = "last_chat_time", nullable = false)
    var lastChatTime: Long,

    @Column(name = "player_id", nullable = false)
    var playerId: Long    // 创建聊天室的玩家ID
) : PublicAsset {

    constructor() : this(
        0, 0, "", "", "", 0, 0L, 0L, 0L
    )

    override fun primaryKey() = id
}

