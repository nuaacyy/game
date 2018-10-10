package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.CHATROOM_NAMED_QUERY
import com.point18.slg2d.common.publicentities.ChatRoomEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import java.io.Serializable
import java.time.Duration
import java.util.*

class ChatRoom(
    var id: Long,
    var publicId: Long,
    var roomName: String,
    var iconProtoIds: LinkedList<Int>,
    var members: LinkedList<ChatPlayerInfo>,
    var memberNum: Int,
    var createTime: Long,
    var lastChatTime: Long,
    var playerId: Long   // 群主玩家Id
) : EntityWrapper<ChatRoomEntity> {
    constructor() : this(
        0, 0, "", LinkedList<Int>(), LinkedList<ChatPlayerInfo>(),
        0, 0L, 0L, 0L
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ChatRoomEntity {
        return ChatRoomEntity(
            id,
            publicId,
            roomName,
            toJson(iconProtoIds),
            toJson(members),
            memberNum,
            createTime,
            lastChatTime,
            playerId
        )
    }

    override fun wrap(entity: ChatRoomEntity) {
        id = entity.id
        publicId = entity.publicId
        roomName = entity.roomName
        iconProtoIds = toObj(entity.iconProtoIds)
        members = toObj(entity.members)
        memberNum = entity.memberNum
        createTime = entity.createTime
        lastChatTime = entity.lastChatTime
        playerId = entity.playerId
    }
}

class ChatPlayerInfo(
    var playerId: Long,
    var iconProto: Int,
    var playerName: String = "",            // 玩家名字
    var vipLv: Int = 0,                     //
    var areaNo: Int = 0,                        //
    var allianceShortName: String = "",         //
    var fightValue: Long = 0,                    //
    var castleLv: Int = 0,
    var playerShortName: String = "",            // 昵称
    var isShow: Boolean  // 是否在聊天室图标中显示
) : Serializable

class CacheChatRoom(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val chatRoomById = OneKeyIndex { it: ChatRoom -> it.id } // 缓存
    val chatRoomByPlayerId = OneKeyIndexSlice({ it: ChatRoom -> it.playerId }, { ita, itb -> ita.id == itb.id })

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    override fun init() {

    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.chatRooms =
                    session.getNamedQuery(CHATROOM_NAMED_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.chatRooms.forEach { entity ->
            try {
                val chatRoom = db.wdb.recover(entity) {
                    ChatRoom()
                }
                this.chatRoomById.insertByKey(chatRoom)
                this.chatRoomByPlayerId.insertByKey(chatRoom)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun createChatRoom(
        roomId: Long,
        playerId: Long,
        time: Long,
        nameRoom: String,
        icon: Int,
        playerName: String,
        vipLv: Int,
        areaNo: Int,
        allianceShortName: String,
        fightValue: Long,
        castleLv: Int,
        nickName: String
    ): ChatRoom {
        val id = roomId
        val iconList = LinkedList<Int>()
        iconList.add(icon)
        val playerList = LinkedList<ChatPlayerInfo>()
        playerList.add(
            ChatPlayerInfo(
                playerId, icon, playerName, vipLv,
                areaNo, allianceShortName, fightValue, castleLv, nickName, true
            )
        )
        val chatRoom = ChatRoom(
            id,
            id,
            nameRoom,
            iconList,
            playerList,
            1,
            time,
            time,
            playerId
        )
        insert(publicCache, chatRoom)
        chatRoomById.insertByKey(chatRoom)
        chatRoomByPlayerId.insertByKey(chatRoom)
        return chatRoom
    }

    //删除数据
    fun deleteChatRoom(chatRoom: ChatRoom) {
        delete(publicCache, chatRoom)
        chatRoomById.deleteByKey(chatRoom)
        chatRoomByPlayerId.deleteByKey(chatRoom)
    }

    fun findChatRoomById(id: Long): ChatRoom? {
        return chatRoomById.findByKey(id)
    }

    fun findChatRoomByPlayerId(playerId: Long): LinkedList<ChatRoom> {
        val list = LinkedList<ChatRoom>()
        chatRoomByPlayerId.findByKey(playerId) { list.add(it) }
        return list
    }

    fun changeChatRoomOwner(oldOwnerId: Long, newOwnerId: Long, roomId: Long): ChatRoom? {
        val list = LinkedList<ChatRoom>()
        chatRoomByPlayerId.findByKey(oldOwnerId) { list.add(it) }
        val room = list.firstOrNull { it.id == roomId } ?: return null
        chatRoomByPlayerId.deleteByKey(room)
        room.playerId = newOwnerId
        chatRoomByPlayerId.insertByKey(room)
        return room
    }
}











