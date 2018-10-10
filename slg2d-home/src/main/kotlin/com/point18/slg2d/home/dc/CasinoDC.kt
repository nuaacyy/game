package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.CASINO_ID_QUERY
import com.point18.slg2d.common.homeentities.CasinoEntity
import com.point18.slg2d.common.homeentities.CasinoPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.ThreeKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class CasinoDC : AbstractDataContainer<List<CasinoEntity>>() {

    val casinoMap = LinkedList<Casino>()

    val casinoMapTwo = ThreeKeyIndex<Long, Int, Int, Casino>({ it.playerId }, { it.palaceId }, { it.palaceLevel })

    override fun initImpl(data: List<CasinoEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val casinoWrap = wdb.recover(it) { Casino() }

            casinoMap += casinoWrap
            casinoMapTwo.insertByKey(casinoWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<CasinoEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(CASINO_ID_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<CasinoEntity>()
            list
        }
        return data
    }

    // 根据玩家id 查询列表
    fun findAllCasino(type: Int, palaceId: Int): Casino? {
        return casinoMapTwo.findByKey(playerId, palaceId, type)
    }


    // 创建数据
    fun createCasino(
        palaceId: Int,
        palaceLevel: Int,
        isBless: Int,
        blessCount: Int,
        useBlessCount: Int,
        atkCount: Int,
        atkBossCount: Int,
        isAtkBoss: Int,
        totalAtkBossCountCount: Int,
        isAtk: Int,
        freeCount: Int,
        openFreeTime: Long
    ): Casino {
        val id = hpm.generateObjIdNew()
        val casino = Casino(
            id,
            palaceId,
            palaceLevel,
            isBless,
            blessCount,
            useBlessCount,
            atkCount,
            atkBossCount,
            isAtkBoss,
            totalAtkBossCountCount,
            isAtk,
            freeCount,
            openFreeTime,
            playerId
        )
        wdb.save(casino)
        casinoMap += casino
        casinoMapTwo.insertByKey(casino)
        return casino
    }

    // 删除该玩家的记录
    fun delCasinosById() {
        val casinosTemp = LinkedList<Casino>()
        for(casino in this.casinoMap){
            casinosTemp += casino
        }
        for(casinoTemp in casinosTemp){
            delCasinoInfo(casinoTemp)
        }
    }

    // 查找当前palaceId
    fun findMyCasinoId(): Int? {
        return casinoMap[0].palaceId
    }

    // 删除一条记录
    fun delCasinoInfo(req: Casino?) {
        if (req == null || req.id == 0L) {
            return
        }

        wdb.delete(req)

        // 删除缓存
        casinoMap.remove(req)
        casinoMapTwo.deleteByKey(req)
    }
}

// 赌场
class Casino(
    var id: Long,
    var palaceId: Int,       // 赌场轮换的id
    var palaceLevel: Int,       // 类型,1:普通,2:精英
    var isBless: Int,           // 是否祝福
    var blessCount: Int,        // 祝福次数
    var useBlessCount: Int,     // 使用的祝福次数
    var atkCount: Int,          // 攻击次数
    var atkBossCount: Int,      // 攻击boss次数
    var isAtkBoss: Int,         // 是否在攻击次数
    var totalAtkBossCount: Int, // boss可攻击的总数
    var isAtk: Int,             //
    var freeCount: Int,
    var openFreeTime: Long,
    var playerId: Long        // 玩家ID
) : EntityWrapper<CasinoEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = CasinoPK(playerId, id)

    override fun toEntity(): CasinoEntity {
        return CasinoEntity(
            id,
            palaceId,
            palaceLevel,
            isBless,
            blessCount,
            useBlessCount,
            atkCount,
            atkBossCount,
            isAtkBoss,
            totalAtkBossCount,
            isAtk,
            freeCount,
            openFreeTime,
            playerId
        )
    }

    override fun wrap(entity: CasinoEntity) {
        id = entity.id
        palaceId = entity.palaceId
        palaceLevel = entity.palaceLevel
        isBless = entity.isBless
        blessCount = entity.blessCount
        useBlessCount = entity.useBlessCount
        atkCount = entity.atkCount
        atkBossCount = entity.atkBossCount
        isAtkBoss = entity.isAtkBoss
        totalAtkBossCount = entity.totalAtkBossCount
        isAtk = entity.isAtk
        freeCount = entity.freeCount
        openFreeTime = entity.openFreeTime
        playerId = entity.playerId
    }
}

