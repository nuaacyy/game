package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ROOM_CHAT_RECORD_NAMED_QUERY
import com.point18.slg2d.common.publicentities.RoomChatRecordEntity
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration

class RoomChatRecord(
    var id: Long,
    var publicId:Long,
    var roomId: Long,
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

) : EntityWrapper<RoomChatRecordEntity> {

    constructor() : this(0, 0,0, 0, "", 0, 0, 0, 0, "", "", "", "",
        0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): RoomChatRecordEntity {
        return RoomChatRecordEntity(
            id,
            publicId,
            roomId,
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
            pltAreaNo,
            allianceId
        )
    }

    override fun wrap(entity: RoomChatRecordEntity) {
        id = entity.id
        publicId = entity.publicId
        roomId = entity.roomId
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
        allianceId = entity.allianceId
    }

}

class CacheRoomChatRecord(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {
    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val roomChatRecordList = OneKeyIndexSlice({ it: RoomChatRecord -> it.roomId },
        { ita, itb -> ita.chatTime == itb.chatTime })

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.roomChatRecords =
                session.getNamedQuery(ROOM_CHAT_RECORD_NAMED_QUERY)
                    .setLong("publicId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.roomChatRecords.forEach { entity ->
            try {
                val chat = db.wdb.recover(entity) {
                    RoomChatRecord()

                }

                this.roomChatRecordList.insertByKey(chat)
            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 清空记录
    fun clearRoomChatRecord(roomId: Long) {
        val roomChatRecords = mutableListOf<RoomChatRecord>()
        roomChatRecordList.findByKey(roomId) { roomChatRecords.add(it) }
        for (each in roomChatRecords) {
            roomChatRecordList.deleteByKey(each)
            delete(publicCache, each)
        }
    }

    // 删除过期过多的记录
    fun deleteRoomChatRecord(roomId: Long) {
        val roomChatRecords = mutableListOf<RoomChatRecord>()
        val nowTime = getNowTime()
        roomChatRecordList.findByKey(roomId) { roomChatRecords.add(it) }
        val floatRecordsNum = roomChatRecords.size - com.point18.slg2d.common.pc.pcs.basicProtoCache.saveChatListMaxNum
        if (floatRecordsNum > 0) {
            val tmp = roomChatRecords.firstOrNull()
            if (tmp != null) {
                roomChatRecords.remove(tmp)
                roomChatRecordList.deleteByKey(tmp)
                delete(publicCache, tmp)
            }
        }
        val keepTime = com.point18.slg2d.common.pc.pcs.basicProtoCache.keepChatRecordTime * 60 * 1000
        for (each in roomChatRecords) {
            if (nowTime - each.chatTime > keepTime) {
                roomChatRecordList.deleteByKey(each)
                delete(publicCache, each)
            }else{
                break
            }
        }
    }


    fun createRoomChatRecord(chatRecord: RoomChatRecord): RoomChatRecord {
        val id = ppm.generateObjIdNew()
        chatRecord.id = id
        val roomId = chatRecord.roomId
        val roomChatRecords = mutableListOf<RoomChatRecord>()
        roomChatRecordList.findByKey(roomId) { roomChatRecords.add(it) }
        val deleteNum = roomChatRecords.size - com.point18.slg2d.common.pc.pcs.basicProtoCache.saveGroupChatListMaxNum
        if (deleteNum > 0) {
            for (index in 0 until deleteNum) {
                val tmp = roomChatRecords.getOrNull(index) ?: continue
                publicCache.roomChatRecordsCache.roomChatRecordList.deleteByKey(tmp)
                delete(publicCache, tmp)
            }
        }
        roomChatRecordList.insertByKey(chatRecord)
        insert(publicCache, chatRecord)
        deleteRoomChatRecord(roomId)
        return chatRecord
    }

    fun getRoomLastChatRecord(roomId: Long): RoomChatRecord? {
        val roomChatRecords = mutableListOf<RoomChatRecord>()
        roomChatRecordList.findByKey(roomId) { roomChatRecords.add(it) }
        return roomChatRecords.lastOrNull()
    }

    fun getRoomChatRecordHistory(chatId: Long, roomId: Long): MutableList<RoomChatRecord> {
        val history = mutableListOf<RoomChatRecord>()
        val roomChatRecords = mutableListOf<RoomChatRecord>()
        roomChatRecordList.findByKey(roomId) { roomChatRecords.add(it) }

        if (chatId == 0L) {
            var startIndex = roomChatRecords.lastIndex - 10
            if (startIndex < 0) {
                startIndex = 0
            }
            for (index in startIndex..roomChatRecords.lastIndex) {
                val tmpChat = roomChatRecords.getOrNull(index) ?: continue
                history.add(tmpChat)
            }

        } else {
            val lastChat = roomChatRecords.firstOrNull { it.id == chatId }

            if (lastChat != null) {
                val indexChat = roomChatRecords.indexOf(lastChat)
                val indexEnd = indexChat - 1
                val indexBegin = indexEnd - 10
                for (index in indexBegin..indexEnd) {
                    val tmpChat = roomChatRecords.getOrNull(index) ?: continue
                    history.add(tmpChat)
                }
            }
        }
        return history
    }
}








