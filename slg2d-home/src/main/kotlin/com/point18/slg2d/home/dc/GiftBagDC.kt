package com.point18.slg2d.home.dc

import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class GiftBagDC : AbstractDataContainer<List<GiftBagEntity>>() {

    val giftBags = OneKeyIndex(GiftBag::giftId)

    override fun initImpl(data: List<GiftBagEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val giftBagWrap = wdb.recover(it) { GiftBag() }
            giftBags.insertByKey(giftBagWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<GiftBagEntity>? {
        return dao.findWithTransaction { session ->
            val list = session.getNamedQuery(GIFT_BAG_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<GiftBagEntity>()
            list
        }
    }

    fun getGiftBag(giftId: Int): GiftBag? {
        return giftBags.findByKey(giftId)
    }

    fun createGiftBag(giftId: Int, playerId: Long, openTime: Long): GiftBag {
        val giftBag = GiftBag(hpm.generateObjIdNew(), playerId, giftId, openTime, 1, 0)
        // 保存
        wdb.save(giftBag)
        giftBags.insertByKey(giftBag)
        return giftBag
    }

    fun delGiftBag(giftBag: GiftBag) {
        wdb.delete(giftBag)
    }
}

class GiftBag(
    var id: Long,
    var playerId: Long, // 玩家编号
    var giftId: Int, // 礼包类型ID
    var endTime: Long, // 该礼包结束时间
    var curLevel: Int, // 当前档位
    var curCount:Int // 当前档位充值次数
) : EntityWrapper<GiftBagEntity> {

    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = GiftBagPK(playerId, id)

    override fun toEntity(): GiftBagEntity {
        return GiftBagEntity(
            id,
            playerId,
            giftId,
            endTime,
            curLevel,
            curCount
        )
    }

    override fun wrap(entity: GiftBagEntity) {
        id = entity.id
        playerId = entity.playerId
        giftId = entity.giftId
        endTime = entity.endTime
        curLevel = entity.curLevel
        curCount = entity.curCount
    }

    fun refresh(endTime: Long) {
        this.endTime = endTime
        this.curLevel = 1
        this.curCount = 0
    }

    fun recordCount(level: Int) {
        if (level == this.curLevel) {
            this.curCount++
            return
        }
        this.curLevel = level
        this.curCount = 1
    }
}