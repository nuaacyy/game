package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.MERCHANT_SHIP_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.MerchantShipEntity
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.pc.pcs
import java.io.Serializable
import java.time.Duration

// 商船数据

class MerchantShipDC : AbstractDataContainer<MerchantShipEntity>() {

    var merchantShip: MerchantShip? = null

    override fun initImpl(data: MerchantShipEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val merchantShipWrap = wdb.recover(data) { MerchantShip() }

            merchantShip = merchantShipWrap
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): MerchantShipEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(MERCHANT_SHIP_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<MerchantShipEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }

        return data
    }

    fun createMerchantShip(
        shopInfoList: HashMap<Int, MerchantShipInfo>
    ): MerchantShip {
        val merchantShip = MerchantShip(
            getNowTime() + pcs.basicProtoCache.tradShipRefreshTime * 1000,
            1,
            playerId,
            shopInfoList
        )
        merchantShip.shopInfoList = shopInfoList

        wdb.save(merchantShip)
        this.merchantShip = merchantShip

        return merchantShip
    }

    // 通过商船位置找到对应的物品
    fun findMerchantShipInfoByExchangeId(exchangeId: Int): MerchantShipInfo? {
        return this.merchantShip?.shopInfoList?.values?.firstOrNull { it.exchangeId == exchangeId }
    }
}

class MerchantShip(
    var nextReTime: Long,  // 下次刷新时间
    var nowTimes: Int, // 当前商船刷新次数属于多少轮  用来保底出惊喜  跟着配置走 比如配置成 最少4次刷新出惊喜 最多6次出
    var playerId: Long,   // 玩家ID
    var shopInfoList: HashMap<Int, MerchantShipInfo> // `sql:"-"` // 商船里的物品信息
) : EntityWrapper<MerchantShipEntity> {
    constructor() : this(0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): MerchantShipEntity {
        return MerchantShipEntity(
            playerId,
            nextReTime,
            nowTimes,
            toJson(shopInfoList)
        )
    }

    override fun wrap(entity: MerchantShipEntity) {
        playerId = entity.playerId
        nextReTime = entity.nextReTime
        nowTimes = entity.nowTimes
        shopInfoList = toObj(entity.shop)
    }

}

class MerchantShipInfo(
    var exchangeId: Int, // 兑换项的记录ID,自然排序ID
    var srcType: Int, // 源物品类型  1-普通物品 2-惊喜物品
    var srcProtoId: Int, // 根据不通的物品类型对应不同的配置表模版唯一ID
    var exchanged: Int, // 是否以成功兑换
    var locked: Int // 是否已经锁定
) {
    constructor() : this(0, 0, 0, 0, 0)
}



