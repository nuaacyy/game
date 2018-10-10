package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.FRIEND_NAMED_QUERY
import com.point18.slg2d.common.homeentities.FriendEntity
import com.point18.slg2d.common.homeentities.FriendPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class FriendDC : AbstractDataContainer<List<FriendEntity>>() {

    val friendMap = OneKeyIndex<Long, Friend> { it.tarPlayerId }

    override fun initImpl(data: List<FriendEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val friendWrap = wdb.recover(it) { Friend() }

            friendMap.insertByKey(friendWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<FriendEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FRIEND_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<FriendEntity>()
            list
        }
        return data
    }

    // 根据玩家id 查询好友列表
    fun findFriendById(): (LinkedList<Friend>) {
        val allFriends = LinkedList<Friend>()
        this.friendMap.map {
            allFriends += it
            true
        }
        return allFriends
    }

    // 创建好友
    fun createFriend(
        tarPlayerId: Long,
        worldId: Long,
        name: String,
        photoId: Int,
        fightValue: Int,
        castleLv: Int,
        castleSkin: Int,
        vipLv: Int,
        areaNo: Int,
        allianceShortName: String,
        isRealFriend: Int,
        shortName: String
    ): Friend {
        val id = hpm.generateObjIdNew()
        val friend = Friend(
            id,
            worldId,
            0,
            tarPlayerId,
            name,
            photoId,
            fightValue,
            castleLv,
            castleSkin,
            vipLv,
            areaNo,
            allianceShortName,
            isRealFriend,
            shortName,
            playerId
        )
        wdb.save(friend)
        this.friendMap.insertByKey(friend)
        return friend
    }

    // 查找同一组的好友
    fun findFriendByGroupId(groupId: Long): (LinkedList<Friend>) {
        val allFriends = LinkedList<Friend>()
        friendMap.index.forEach {
            if (it.value.groupId == groupId) {
                allFriends += it.value
            }
        }
        return allFriends
    }

    // 根据玩家id 对方id获取好友
    fun findMyFriendById(targetId: Long): (Friend?) {
        return this.friendMap.findByKey(targetId)
    }

    // 删除一条记录
    fun delFriendInfo(req: Friend?) {
        if (req == null || req.id == 0L) {
            return
        }

        wdb.delete(req)

        // 删除缓存
        friendMap.deleteByKey(req)
    }
}

// 好友
class Friend(
    var id: Long,
    var worldId: Long,                   // 世界id
    var groupId: Long,       // 群组id
    var tarPlayerId: Long,       // 好友id
    var name: String,
    var photoId: Int,
    var fightValue: Int,
    var castleLv: Int,
    var castleSkin: Int,
    var vipLv: Int,
    var areaNo: Int,
    var allianceShortName: String,
    var isRealFriend: Int,
    var shortName: String,
    var playerId: Long        // 玩家ID
) : EntityWrapper<FriendEntity> {
    constructor() : this(0, 0, 0, 0, "", 0, 0, 0, 0, 0, 0, "", 0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = FriendPK(playerId, id)

    override fun toEntity(): FriendEntity {
        return FriendEntity(
            id,
            worldId,
            groupId,
            tarPlayerId,
            name,
            photoId,
            fightValue,
            castleLv,
            castleSkin,
            vipLv,
            areaNo,
            allianceShortName,
            isRealFriend,
            shortName,
            playerId
        )
    }

    override fun wrap(entity: FriendEntity) {
        id = entity.id
        worldId = entity.worldId
        groupId = entity.groupId
        tarPlayerId = entity.tarPlayerId
        name = entity.name
        photoId = entity.photoId
        fightValue = entity.fightValue
        castleLv = entity.castleLv
        castleSkin = entity.castleSkin
        vipLv = entity.vipLv
        areaNo = entity.areaNo
        allianceShortName = entity.allianceShortName
        isRealFriend = entity.isRealFriend
        shortName = entity.shortName
        playerId = entity.playerId
    }
}

