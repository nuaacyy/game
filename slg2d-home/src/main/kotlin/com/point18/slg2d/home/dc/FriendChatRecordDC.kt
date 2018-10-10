package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.homeentities.FRIENDCHATRECORD_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.FriendChatRecordEntity
import com.point18.slg2d.common.homeentities.FriendChatRecordPK
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class FriendChatRecordDC : AbstractDataContainer<List<FriendChatRecordEntity>>() {

    val recordsList = LinkedList<FriendChatRecord>()
    val recordsMap =
        OneKeyIndexSlice<Long, FriendChatRecord>({ it.friendId }, { ita, itb -> ita.talkTime == itb.talkTime })

    override fun initImpl(data: List<FriendChatRecordEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val recordWrap = wdb.recover(it) { FriendChatRecord() }

            recordsMap.insertByKey(recordWrap)
            recordsList += recordWrap
        }
        deleteOutOfDate() // 删除一些过期的聊天记录
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<FriendChatRecordEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FRIENDCHATRECORD_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<FriendChatRecordEntity>()
            list
        }
        return data
    }

    fun findRecordByFriendId(friendId: Long): List<FriendChatRecord> {
        val list = LinkedList<FriendChatRecord>()
        this.recordsMap.findByKey(friendId) {
            list += it
            true
        }
        return list
    }

    fun delRecordByFriendId(friendId: Long) {
        val list = LinkedList<FriendChatRecord>()
        this.recordsMap.findByKey(friendId) {
            list += it
            true
        }

        for (eachRemove in list) {
            this.recordsMap.deleteByKey(eachRemove)
            this.recordsList.remove(eachRemove)
        }
    }

    fun deleteOutOfDate() {
        val now = getNowTime()
        val removeList = LinkedList<FriendChatRecord>()
        for (each in recordsList) {
            if (now - each.talkTime > pcs.basicProtoCache.keepChatRecordTime * 60 * 1000) {
                removeList += each
            }
        }

        for (eachRemove in removeList) {
            this.recordsMap.deleteByKey(eachRemove)
            this.recordsList.remove(eachRemove)
        }
    }

    fun createFriendChatRecord(
        lastTalkTime: Long,
        iconId: Int,
        recordString: String,
        friendId: Long,
        msgType: Int,
        vipLv: Int,
        alliancePos: Int,
        allianceName: String,
        allianceShortName: String,
        playerName: String,
        playerShortName: String,
        kingdomPos: Int,
        wonderPos: Int,
        talkPlayerId: Long,
        areaNo: Int
    ): FriendChatRecord {
        val id = hpm.generateObjIdNew()
        val record = FriendChatRecord(
            id, friendId, recordString, iconId, lastTalkTime, msgType, vipLv,
            alliancePos, allianceName, allianceShortName, playerName, playerShortName, kingdomPos,
            wonderPos, talkPlayerId, areaNo, playerId
        )

        wdb.save(record)
        this.recordsMap.insertByKey(record)
        this.recordsList += record

        return record
    }
}

class FriendChatRecord(
    var id: Long,
    var friendId: Long,   // 和哪个人说
    var record: String,
    var iconId: Int,
    var talkTime: Long,  // 聊天时间

    var msgType: Int,     // 消息类型  1-普通消息  2-红包消息（暂时被砍） 3-表情  4-战报分享  5-集结  6-喇叭
    var vipLv: Int,        // vip
    var alliancePos: Int,  // 联盟职位
    var allianceName: String,  // 联盟名称
    var allianceShortName: String,    // 联盟简称
    var playerName: String,
    var playerShortName: String,
    var kingdomPos: Int,// 王国职位
    var wonderPos: Int, // 大帝官职
    var talkPlayerId: Long, // 这个说话人可以是自己,可以是别人
    var areaNo: Int,       // 说话人的区号
    var playerId: Long
) : EntityWrapper<FriendChatRecordEntity> {

    constructor() : this(
        0, 0, "", 0, 0, 0, 0, 0,
        "", "", "", "", 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = FriendChatRecordPK(playerId, id)

    override fun toEntity(): FriendChatRecordEntity {
        return FriendChatRecordEntity(
            id,
            friendId,
            record,
            iconId,
            talkTime,
            msgType,
            vipLv,
            alliancePos,
            allianceName,
            allianceShortName,
            playerName,
            playerShortName,
            kingdomPos,
            wonderPos,
            talkPlayerId,
            areaNo,
            playerId
        )
    }

    override fun wrap(entity: FriendChatRecordEntity) {
        id = entity.id
        iconId = entity.iconId
        playerId = entity.playerId
        talkTime = entity.lastTalkTime
        msgType = entity.msgType
        friendId = entity.friendId
        record = entity.record
        vipLv = entity.vipLv
        alliancePos = entity.alliancePos
        allianceName = entity.allianceName
        allianceShortName = entity.allianceShortName
        playerName = entity.playerName
        playerShortName = entity.playerShortName
        kingdomPos = entity.kingdomPos
        wonderPos = entity.wonderPos
        talkPlayerId = entity.talkPlayerId
        areaNo = entity.areaNo
    }
}