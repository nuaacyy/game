package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
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

class CasinoPlayerDC : AbstractDataContainer<List<CasinoPlayerEntity>>() {

    val casinoPlayerMap = OneKeyIndex<Long, CasinoPlayer> { it.playerId }

    override fun initImpl(data: List<CasinoPlayerEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val casinoPlayerWrap = wdb.recover(it) { CasinoPlayer() }

            casinoPlayerMap.insertByKey(casinoPlayerWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<CasinoPlayerEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(CASINO_PLAYER_ID_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<CasinoPlayerEntity>()
            list
        }
        return data
    }

    // 根据玩家id 查询列表
    fun findAllCasinoPlayer(): (LinkedList<CasinoPlayer>) {
        val allCasinoPlayers = LinkedList<CasinoPlayer>()
        this.casinoPlayerMap.map {
            allCasinoPlayers += it
            true
        }
        return allCasinoPlayers
    }

    // 根据玩家id 查询列表
    fun findCasinoPlayer(): CasinoPlayer? {
        return this.casinoPlayerMap.findByKey(playerId)
    }

    // 创建数据
    fun createCasinoPlayer(
        palaceId: Int,
        finishDate: Long
    ): CasinoPlayer {
        val id = hpm.generateObjIdNew()
        val casinoPlayer = CasinoPlayer(
            id,
            palaceId,
            finishDate,
            playerId
        )
        wdb.save(casinoPlayer)
        this.casinoPlayerMap.insertByKey(casinoPlayer)
        return casinoPlayer
    }

    // 根据玩家id
    fun findMyCasinoPlayerById(): (CasinoPlayer?) {
        return this.casinoPlayerMap.findByKey(playerId)
    }

    // 删除一条记录
    fun delCasinoPlayerInfo(req: CasinoPlayer?) {
        if (req == null || req.id == 0L) {
            return
        }

        wdb.delete(req)

        // 删除缓存
        casinoPlayerMap.deleteByKey(req)
    }
}

// 赌场
class CasinoPlayer(
    var id: Long,
    var palaceId: Int,       // 赌场轮换的id
    var finishDate: Long,     // 切换的时间
    var playerId: Long        // 玩家ID
) : EntityWrapper<CasinoPlayerEntity> {
    constructor() : this(0, 0, 0L, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = CasinoPlayerPK(playerId, id)

    override fun toEntity(): CasinoPlayerEntity {
        return CasinoPlayerEntity(
            id,
            palaceId,
            finishDate,
            playerId
        )
    }

    override fun wrap(entity: CasinoPlayerEntity) {
        id = entity.id
        palaceId = entity.palaceId
        finishDate = entity.finishDate
        playerId = entity.playerId
    }
}

