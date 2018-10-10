package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.BLACK_PLAYER_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.BlackPlayerEntity
import com.point18.slg2d.common.homeentities.BlackPlayerPK
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

class BlackPlayerDC : AbstractDataContainer<List<BlackPlayerEntity>>() {

    val blackPlayers = LinkedList<BlackPlayer>()
    val blackPlayerMap = OneKeyIndex<Long, BlackPlayer> { it.blackPlayerId }

    override fun initImpl(data: List<BlackPlayerEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val blackPlayerWrap = wdb.recover(it) { BlackPlayer() }

            blackPlayers += blackPlayerWrap
            blackPlayerMap.insertByKey(blackPlayerWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<BlackPlayerEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(BLACK_PLAYER_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<BlackPlayerEntity>()
            list
        }
        return data
    }

    // 加入黑名单
   fun createBlackPlayer(
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
        shortName: String
    ): BlackPlayer {
        val id = hpm.generateObjIdNew()
        val blackPlayer = BlackPlayer(id,worldId, tarPlayerId,name,photoId,fightValue,castleLv,castleSkin,vipLv,areaNo,allianceShortName,shortName,playerId)
        wdb.save(blackPlayer)

        // 保存到缓存
        this.blackPlayers += blackPlayer
        blackPlayerMap.insertByKey(blackPlayer)
        return blackPlayer
    }

    // 黑名单里所有人
    fun findAllBlackPlayers(): LinkedList<BlackPlayer> {
        val allBlackPlayers = LinkedList<BlackPlayer>()
        this.blackPlayerMap.map {
            allBlackPlayers += it
            true
        }
        return allBlackPlayers
    }

    fun findMyBlackById(targetId: Long): BlackPlayer? {
        return this.blackPlayerMap.findByKey(targetId)
    }

    // 从黑名单中删除
    fun delBlackPlayer(del: BlackPlayer?) {
        if (del == null || del.id == 0L) {
            return
        }

        wdb.delete(del)

        // 删除缓存
        this.blackPlayers.removeIf { it.id == del.id }

        blackPlayerMap.deleteByKey(del)
    }
}
// 世界id
class BlackPlayer(
    var id: Long,
    var worldId: Long,
    var blackPlayerId: Long,    // 黑名单玩家id
    var name: String,           // 玩家名字
    var photoId: Int,           // 头像
    var fightValue: Int,        // 势力值
    var castleLv: Int,          // 城堡等级
    var castleSkin: Int,        // 皮肤
    var vipLv: Int,             // vip等级
    var areaNo: Int,            // 服务器编号
    var allianceShortName: String,  //   联盟简称
    var shortName: String,       //
    var playerId: Long      //
) : EntityWrapper<BlackPlayerEntity> {
    constructor() : this(0, 0, 0, "",0,0,0,0,0,0,"","",0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = BlackPlayerPK(playerId, id)

    override fun toEntity(): BlackPlayerEntity {
        return BlackPlayerEntity(
            id,
            worldId,
            blackPlayerId,
            name,
            photoId,
            fightValue,
            castleLv,
            castleSkin,
            vipLv,
            areaNo,
            allianceShortName,
            shortName,
            playerId
        )
    }

    override fun wrap(entity: BlackPlayerEntity) {
        id = entity.id
        worldId = entity.worldId
        blackPlayerId = entity.blackPlayerId
        name = entity.name
        photoId = entity.photoId
        fightValue = entity.fightValue
        castleLv = entity.castleLv
        castleSkin = entity.castleSkin
        vipLv = entity.vipLv
        areaNo = entity.areaNo
        allianceShortName = entity.allianceShortName
        shortName = entity.shortName
        playerId = entity.playerId
    }
}
