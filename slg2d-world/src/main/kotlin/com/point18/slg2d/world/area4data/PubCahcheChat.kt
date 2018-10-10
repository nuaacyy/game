package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.TEXT_READ_INFO
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.CHAT_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ChatEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 世界聊天
class Chat(
    var worldId: Long,
    var id: Long,
    var msg: String,      // 消息内容
    var msgType: Int,     // 消息类型  1-普通消息  2-红包消息（暂时被砍） 3-表情  4-战报分享  5-集结  6-喇叭
    var readType: Int,    // 读取类型
    var vipLv: Int,        // vip
    var iconProtoId: Int,
    var alliancePos: Int,  // 联盟职位
    var allianceName: String,  // 联盟名称
    var allianceShortName: String,    // 联盟简称
    var playerName: String,
    var playerShortName: String,
    var kingdomPos: Int,// 王国职位
    var wonderPos: Int, // 大帝官职
    var chatTime: Long,  // 聊天时间
    var playerId: Long    // 玩家ID
) : EntityWrapper<ChatEntity> {

    constructor() : this(
        0, 0, "", 0, TEXT_READ_INFO, 0, 0, 0,
        "", "", "", "", 0,
        0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ChatEntity {
        return ChatEntity(
            worldId,
            id,
            msg,
            msgType,
            readType,
            vipLv,
            iconProtoId,
            alliancePos,
            allianceName,
            allianceShortName,
            playerName,
            playerShortName,
            kingdomPos,
            wonderPos,
            chatTime,
            playerId
        )
    }

    override fun wrap(entity: ChatEntity) {
        worldId = entity.worldId
        id = entity.id
        msg = entity.msg
        msgType = entity.msgType
        readType = entity.readType
        vipLv = entity.vipLv
        iconProtoId = entity.iconProtoId
        alliancePos = entity.alliancePos
        allianceName = entity.allianceName
        allianceShortName = entity.allianceShortName
        playerName = entity.playerName
        playerShortName = entity.playerShortName
        kingdomPos = entity.kingdomPos
        wonderPos = entity.wonderPos
        chatTime = entity.chatTime
        playerId = entity.playerId
    }
}

class CacheChat(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val chatMap = OneKeyIndex { it: Chat -> it.id }
    private var chatList = LinkedList<Chat>()

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.chatEntity =
                session.getNamedQuery(CHAT_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.chatEntity.forEach { entity ->
            try {
                val chat = db.wdb.recover(entity) { Chat() }

                this.chatMap.insertByKey(chat)
                this.chatList.add(chat)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun deleteChatInfoByPlayerId(playerId: Long) {
        val newChatList = LinkedList<Chat>()
        for (chat in chatList) {
            if (chat.playerId == playerId) {
                chatMap.deleteByKey(chat)
            } else {
                newChatList.add(chat)
            }
        }

        chatList = newChatList
    }

    fun createChat(chat: Chat): Chat {
        val id = wpm.generateObjIdNew(areaCache)
        val nowTime = getNowTime()
        chat.chatTime = nowTime
        chat.id = id
        chat.worldId = worldId

        if (chatList.size >= pcs.basicProtoCache.saveWorldChatListMaxNum) {
            val tmp = chatList[0]
            chatMap.deleteByKey(tmp)
            chatList.remove(tmp)
            delete(areaCache, tmp)
        }

        insert(areaCache, chat)
        chatMap.insertByKey(chat)
        chatList.add(chat)

        return chat
    }

    fun getWorldChatHistory(id: Long): List<Chat> {
        val history = LinkedList<Chat>()
        val list = chatList.toList()
        if (id == 0L) {
            // 给最新的n条
            if (list.size <= 10) {
                history += list
                history.sortByDescending { it.chatTime }
                return history
            }

            for (index in (list.lastIndex - 10)..list.lastIndex) {
                val tmpChat = list.getOrNull(index) ?: continue
                history.add(tmpChat)
            }

        } else {
            val lastChat = chatList.firstOrNull { it.id == id }

            if (lastChat != null) {
                val indexChat = list.indexOf(lastChat)
                val indexEnd = indexChat - 1
                val indexBegin = indexEnd - 10
                for (index in indexBegin..indexEnd) {
                    val tmpChat = list.getOrNull(index) ?: continue
                    history.add(tmpChat)
                }
            }
        }

        history.sortByDescending { it.chatTime }
        return history

    }
}