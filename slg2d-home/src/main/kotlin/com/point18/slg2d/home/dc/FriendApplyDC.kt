package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.FRIEND_APPLY_WAIT_STATE
import com.point18.slg2d.common.homeentities.FRIEND_APPLY_NAMED_QUERY
import com.point18.slg2d.common.homeentities.FriendApplyEntity
import com.point18.slg2d.common.homeentities.FriendApplyPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class FriendApplyDC : AbstractDataContainer<List<FriendApplyEntity>>() {

    val friendApplyMap = OneKeyIndex<Long, FriendApply> { it.applyPlayerId }

    override fun initImpl(data: List<FriendApplyEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val friendApplyWrap = wdb.recover(it) { FriendApply() }

            friendApplyMap.insertByKey(friendApplyWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<FriendApplyEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FRIEND_APPLY_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<FriendApplyEntity>()
            list
        }
        return data
    }

    fun createFriendApply(
        id: Long,
        worldId: Long,
        applyPlayerId: Long,
        applyPlayerPhoto: Int,
        applyPlayerName: String,
        applyPlayerAreaNo: Int,
        applyPlayerVipLv: Int,
        applyPlayerAllianceShortName: String,
        applyCastleLv: Int,
        applyCastleSkin: Int,
        applyPlayerShortName: String,
        playerId: Long
    ): FriendApply {
        val nowTime = getNowTime()

        val friendApply = FriendApply(
            id,
            worldId,
            applyPlayerId,
            applyPlayerName,
            applyPlayerPhoto,
            applyPlayerAreaNo,
            applyPlayerVipLv,
            applyPlayerAllianceShortName,
            FRIEND_APPLY_WAIT_STATE,
            nowTime,
            applyCastleLv,
            applyCastleSkin,
            applyPlayerShortName,
            playerId
        )

        wdb.save(friendApply)

        // 保存到缓存
        this.friendApplyMap.insertByKey(friendApply)
        return friendApply
    }

    // 根据玩家id获取
    fun findPlayerFriendApply(): LinkedList<FriendApply> {
        val friendApplyList = LinkedList<FriendApply>()
        this.friendApplyMap.map {
            friendApplyList += it
            true
        }
        return friendApplyList
    }

    // 根据玩家id,目标id获取
    fun findMyFriendApplyById(targetPlayerId: Long): FriendApply? {
        return this.friendApplyMap.findByKey(targetPlayerId)
    }

    // 删除一条记录
    fun delFriendApply(del: FriendApply?) {
        if (del == null || del.id == 0L) {
            return
        }

        wdb.delete(del)

        // 删除缓存

        this.friendApplyMap.deleteByKey(del)
    }
}

// 投资银行
class FriendApply(
    var id: Long,
    var worldId: Long,
    var applyPlayerId: Long,                                  // 加我为好友的玩家ID
    var applyPlayerName: String,                                      // 加我为好友的玩家名
    var applyPlayerPhotoId: Int,                                      // 加我为好友的玩家头像
    var applyPlayerAreaNo: Int,                                              // 加我为好友的玩家所在服务器编号
    var applyPlayerVipLv: Int,                                              // 加我为好友的玩家VIP等级
    var applyPlayerAllianceShortName: String,                                      // 加我为好友的玩家联盟简称
    var applyState: Int,                                              // 玩家的处理状态 0-未处理 1-已接受 2-已拒绝
    var applyTime: Long,  // 数据生成时间
    var applyCastleLv: Int,
    var applyCastleSkin: Int,
    var applyPlayerShortName: String,
    var playerId: Long         // 玩家ID
) : EntityWrapper<FriendApplyEntity> {
    constructor() : this(0, 0, 0, "", 0, 0, 0, "", 0, 0, 0, 0, "",0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = FriendApplyPK(playerId, id)

    override fun toEntity(): FriendApplyEntity {
        return FriendApplyEntity(
            id,
            worldId,
            applyPlayerId,
            applyPlayerName,
            applyPlayerPhotoId,
            applyPlayerAreaNo,
            applyPlayerVipLv,
            applyPlayerAllianceShortName,
            applyState,
            applyTime,
            applyCastleLv,
            applyCastleSkin,
            applyPlayerShortName,
            playerId
        )
    }

    override fun wrap(entity: FriendApplyEntity) {
        id = entity.id
        worldId = entity.worldId
        applyPlayerId = entity.applyPlayerId
        applyPlayerName = entity.applyPlayerName
        applyPlayerPhotoId = entity.applyPlayerPhotoId
        applyPlayerAreaNo = entity.applyPlayerAreaNo
        applyPlayerVipLv = entity.applyPlayerVipLv
        applyPlayerAllianceShortName = entity.applyPlayerAllianceShortName
        applyState = entity.applyState
        applyTime = entity.applyTime
        applyCastleLv = entity.applyCastleLv
        applyCastleSkin = entity.applyCastleSkin
        applyPlayerShortName = entity.applyPlayerShortName
        playerId = entity.playerId
    }
}

