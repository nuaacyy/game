package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.JJC_SHOP_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.JjcHomeEntity
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.HAVE_NOT_BOUGHT
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.pc.pcs
import java.io.Serializable
import java.time.Duration
import java.util.*

class JjcHomeDC : AbstractDataContainer<JjcHomeEntity>() {

    lateinit var jjcHome: JjcHome

    override fun initImpl(data: JjcHomeEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val jjcHomeWrap = wdb.recover(data) { JjcHome() }
            jjcHome = jjcHomeWrap
        }else{
            val infoList: LinkedList<ItemInfo> = LinkedList()
            val randProtoIds = LinkedList<Int>()

            while (true) {
                val protoNum = pcs.arenaShopProtoCache.mapOfItems.size
                val randItemProtoId = getRandInt(protoNum)
                if (!randProtoIds.contains(randItemProtoId) && pcs.arenaShopProtoCache.mapOfItems[randItemProtoId] != null) {
                    randProtoIds += randItemProtoId
                }
                if (randProtoIds.size == pcs.basicProtoCache.arenaShopNum) {
                    break
                }
            }

            var gridId = 1
            for (protoId in randProtoIds) {
                val tmp = ItemInfo(gridId, HAVE_NOT_BOUGHT, protoId)
                infoList += tmp
                gridId++
            }

            val jjcShop = JjcHome(
                playerId,
                infoList,
                getNowTime(),
                0,
                0,
                0,
                5
            )

            wdb.save(jjcShop)
            jjcHome = jjcShop
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): JjcHomeEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(JJC_SHOP_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<JjcHomeEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }
}

class JjcHome(
    var playerId: Long,
    var itemsInfo: LinkedList<ItemInfo>,
    var refreshShopTime: Long,           // 竞技场商店刷新的时间
    var arenaRefreshShopTimes: Int,      // 竞技场商店刷新的次数，根据次数递进增加刷新消耗
    var lastFightResetTime: Long,         // 上次竞技场挑战次数购买或者重置的时间
    var todayBuyCountNum: Int,            // 今天买了多少次竞技场挑战次数
    var todayNum: Int                     // 今天剩余的竞技场
) : EntityWrapper<JjcHomeEntity> {
    var scoreRewards: LinkedList<Int> = LinkedList()        // 本日已领取的积分奖励模版ID
    var drawRewards: LinkedList<Int> = LinkedList()         //已领取的竞技场奖励模版ID（历史最高排名奖励）
    var achievementRewards: LinkedList<Int> = LinkedList()  // 成就兑换

    constructor() : this(
        0, LinkedList(), getNowTime(), 0, 0, 0, 5
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): JjcHomeEntity {
        return JjcHomeEntity(
            playerId,
            toJson(itemsInfo),
            refreshShopTime,
            arenaRefreshShopTimes,
            toJson(scoreRewards),
            toJson(drawRewards),
            toJson(achievementRewards),
            lastFightResetTime,
            todayBuyCountNum,
            todayNum
        )
    }

    override fun wrap(entity: JjcHomeEntity) {
        playerId = entity.playerId
        itemsInfo = toObj(entity.itemsInfo)
        refreshShopTime = entity.refreshTime
        arenaRefreshShopTimes = entity.times
        lastFightResetTime = entity.lastBuyCountTime
        todayBuyCountNum = entity.todayBuyCountNum
        todayNum = entity.todayNum
    }

}

data class ItemInfo(
    var gridId: Int = 0,
    var haveBought: Int = 0,
    var protoId: Int = 0
)