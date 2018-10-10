package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_CHAT_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceChatEntity
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration

class AllianceChat(
    var id: Long,
    var areaNo: Int,                  // 所属服务器编号
    var msg: String,                  // msg内容
    var msgType: Int,                 // 类型  1 文本 2.。。。。
    var vipLv: Int,                   // vip等级
    var alliancePos: Int,             // 联盟官职
    var iconProtoId: Int,              // 头像
    var allianceName: String,         // 联盟简称
    var allianceShortName: String,    // 联盟简称
    var playerName: String,           // 玩家简称
    var playerShortName: String,      // 简称
    var kingdomPos: Int,              // 王国职位
    var wonderPos: Int,               // 大帝职位
    var chatTime: Long,               // 聊天时间
    var playerId: Long,               // 发言人id
    var pltAreaNo: Long,              // 所属服务器 唯一标识
    var allianceId: Long              // 联盟ID

) : EntityWrapper<AllianceChatEntity> {

    constructor() : this(
        0, 0, "", 0, 0, 0, 0, "", "", "", "",
        0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceChatEntity {
        return AllianceChatEntity(
            id,
            allianceId,
            areaNo,
            msg,
            msgType,
            vipLv,
            alliancePos,
            iconProtoId,
            allianceName,
            allianceShortName,
            playerName,
            playerShortName,
            kingdomPos,
            wonderPos,
            chatTime,
            playerId,
            pltAreaNo
        )
    }

    override fun wrap(entity: AllianceChatEntity) {
        id = entity.id
        allianceId = entity.allianceId
        areaNo = entity.areaNo
        msg = entity.msg
        msgType = entity.msgType
        vipLv = entity.vipLv
        alliancePos = entity.alliancePos
        iconProtoId = entity.iconProtoId
        allianceName = entity.allianceName
        allianceShortName = entity.allianceShortName
        playerName = entity.playerName
        playerShortName = entity.playerShortName
        kingdomPos = entity.kingdomPos
        wonderPos = entity.wonderPos
        chatTime = entity.chatTime
        playerId = entity.playerId
        pltAreaNo = entity.pltAreaNo
    }

}

class CacheAllianceChat(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceChatMap = TwoKeyIndex({ it: AllianceChat -> it.allianceId },
        { it: AllianceChat -> it.id }) // 帮派ID key

    val allianceChatList = OneKeyIndexSlice({ it: AllianceChat -> it.allianceId },
        { ita, itb -> ita.id == itb.id })

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceChats =
                    session.getNamedQuery(ALLIANCE_CHAT_NAMED_QUERY)
                        .setLong("allianceId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceChats.forEach { entity ->
            try {
                val chat = db.wdb.recover(entity) {
                    AllianceChat()

                }

                this.allianceChatMap.insertByKey(chat)
                this.allianceChatList.insertByKey(chat)
            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceChat(chat: AllianceChat): AllianceChat {
        val id = ppm.generateObjIdNew()
        chat.id = id
        val allianceId = chat.allianceId
        val nowTime = getNowTime()
        val allianceChats = mutableListOf<AllianceChat>()
        allianceChatList.findByKey(allianceId) { allianceChats.add(it) }
        allianceChats.sortBy { it.chatTime }
        if (allianceChats.size >= com.point18.slg2d.common.pc.pcs.basicProtoCache.saveGroupChatListMaxNum) {
            val tmp = allianceChats[0]
            allianceChatMap.deleteByKey(tmp)
            allianceChatList.deleteByKey(tmp)
            delete(publicCache, tmp)
        }

        val keepTime = com.point18.slg2d.common.pc.pcs.basicProtoCache.keepChatRecordTime * 60 * 1000
        for (each in allianceChats) {
            if (nowTime - each.chatTime > keepTime) {
                allianceChatList.deleteByKey(each)
                allianceChatMap.deleteByKey(each)
                delete(publicCache, each)
            } else {
                break
            }
        }

        allianceChatMap.insertByKey(chat)
        allianceChatList.insertByKey(chat)
        insert(publicCache, chat)
        return chat
    }

    fun getAllianceChatHistory(chatId: Long, allianceId: Long): MutableList<AllianceChat> {
        val history = mutableListOf<AllianceChat>()
        val allianceChats = mutableListOf<AllianceChat>()
        allianceChatList.findByKey(allianceId) { allianceChats.add(it) }
        allianceChats.sortBy { it.chatTime }

        if (chatId == 0L) {
            // 给最新的n条
            if (allianceChats.size <= 10) {
                history.addAll(allianceChats)
                history.sortByDescending { it.chatTime }
                return history
            }

            for (index in (allianceChats.lastIndex - 10)..allianceChats.lastIndex) {
                val tmpChat = allianceChats.getOrNull(index) ?: continue
                history.add(tmpChat)
            }

        } else {
            val lastChat = allianceChats.firstOrNull { it.id == chatId }

            if (lastChat != null) {
                val indexChat = allianceChats.indexOf(lastChat)
                val indexEnd = indexChat - 1
                val indexBegin = indexEnd - 10
                for (index in indexBegin..indexEnd) {
                    val tmpChat = allianceChats.getOrNull(index) ?: continue
                    history.add(tmpChat)
                }
            }
        }

        history.sortByDescending { it.chatTime }
        return history

    }
}

