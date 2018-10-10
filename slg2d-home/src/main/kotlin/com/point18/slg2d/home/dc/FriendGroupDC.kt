package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.FRIEND_GROUP_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.FriendGroupEntity
import com.point18.slg2d.common.homeentities.FriendGroupPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class FriendGroupDC : AbstractDataContainer<List<FriendGroupEntity>>() {

    val friendGroups = LinkedList<FriendGroup>()

    override fun initImpl(data: List<FriendGroupEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val friendGroupWrap = wdb.recover(it) { FriendGroup() }
            friendGroups += friendGroupWrap
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<FriendGroupEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FRIEND_GROUP_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<FriendGroupEntity>()
            list
        }
        return data
    }

    fun createFriendGroup(groupName: String): FriendGroup {
        val groupId = hpm.generateObjIdNew()
        val friendGroup = FriendGroup(groupId, groupName, playerId)
        wdb.save(friendGroup)

        // 保存到缓存
        this.friendGroups += friendGroup
        return friendGroup
    }

    // 根据玩家id获取好友群组
    fun findPlayerFriendGroup(): LinkedList<FriendGroup> {
        return this.friendGroups
    }

    // 根据玩家id,群组id获取群组
    fun findMyFriendGroupById(groupId: Long): FriendGroup? {
        return this.friendGroups.firstOrNull { it.id == groupId }
    }

    // 删除一条记录
    fun delFriendGroup(del: FriendGroup?) {
        if (del == null || del.id == 0L) {
            return
        }

        wdb.delete(del)

        // 删除缓存
        this.friendGroups.removeIf { it.id == del.id }
    }
}

class FriendGroup(
    var id: Long,

    var groupName: String,    // 分组名称
    var playerId: Long      //
) : EntityWrapper<FriendGroupEntity> {
    constructor() : this(0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = FriendGroupPK(playerId, id)

    override fun toEntity(): FriendGroupEntity {
        return FriendGroupEntity(
            id,
            groupName,
            playerId
        )
    }

    override fun wrap(entity: FriendGroupEntity) {
        id = entity.id
        groupName = entity.groupName
        playerId = entity.playerId
    }
}
