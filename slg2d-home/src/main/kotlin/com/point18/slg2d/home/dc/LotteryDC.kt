package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.homeentities.LOTTERY_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.LotteryEntity
import com.point18.slg2d.common.homeentities.LotteryPK
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.DrawHeroProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.lottery.getDiscountRefreshTime
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class LotteryDC : AbstractDataContainer<List<LotteryEntity>>() {
    private val lotterys = OneKeyIndex<Int, Lottery> { it.protoId }

    override fun initImpl(data: List<LotteryEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val heroWrap = wdb.recover(it) { Lottery() }

            lotterys.insertByKey(heroWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<LotteryEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(LOTTERY_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<LotteryEntity>()
            list
        }
        return data
    }

    fun findDrawHeroByPlayerId(playerId: Long): LinkedList<Lottery> {
        val drawList = LinkedList<Lottery>()
        lotterys.index.forEach { drawList += it.value }
        return drawList
    }

    fun createDrawHero(
        protoId: Int,
        activityOverTime: Long,
        restFreeDrawTimes: Int,
        playerId: Long
    ): (Lottery) {
        val id = hpm.generateObjIdNew()
        val drawHero = Lottery(
            id, 0, protoId, 1, activityOverTime, playerId, restFreeDrawTimes,
            0, 0, 0, 0, 0
        )
        lotterys.insertByKey(drawHero)
        wdb.save(drawHero)
        return drawHero
    }

    fun deleteDrawHero(drawHero: Lottery?) {
        if (drawHero == null || drawHero.id == 0L) {
            return
        }

        if (drawHero.id == 0L) {
            return
        }

        wdb.delete(drawHero)

        // 从缓存中删除
        lotterys.deleteByKey(drawHero)
        // 额外删除心跳
    }

    fun initLotteryInfo(session: PlayerActor) {
        // 从数据库找
        val now = getNowTime()
        var lotteryPools = findDrawHeroByPlayerId(session.playerId)

        // 应该有的抽卡proto
        val newLotteryList = LinkedList<DrawHeroProto>()
        for ((_, eachProto) in pcs.drawHeroProtoCache.dropBagMap) {
            if (eachProto.actEndTime > now && eachProto.actStartTime < now) {
                newLotteryList += eachProto
                continue
            }
            if (eachProto.actEndTime == 0L && eachProto.actStartTime == 0L) {
                newLotteryList += eachProto
            }
        }

        for (eachActivity in newLotteryList) {
            val act = lotteryPools.filter { it.protoId == eachActivity.id }

            if (act.size == 1) {
                continue
            }

            if (act.size > 1) {
                normalLog.error("抽奖池有相同的，错误！")
                continue
            }

            // 如果抽奖没有创建
            if (eachActivity.id == 1) {
                createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    pcs.basicProtoCache.lowDropFreeNum, session.playerId
                )
            } else if (eachActivity.id == 2) {
                createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    1, session.playerId
                )
            } else {
                createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    0, session.playerId
                )
            }
        }

        lotteryPools = findDrawHeroByPlayerId(session.playerId)

        for (eachLottery in lotteryPools) {
            // 验证模板
            val proto = pcs.drawHeroProtoCache.dropBagMap[eachLottery.protoId]
            if (proto == null) {
                continue
            }

            // 通过模板验证是否在活动时间
            if (now > proto.actEndTime || now < proto.actStartTime) {
                if (proto.actEndTime != 0L && proto.actStartTime != 0L) {
                    continue
                }
            }

            // 普通
            if (eachLottery.protoId == 1) {
                // 免费次数同时刷新
                val lastFreeDrawTime = getDiscountRefreshTime(eachLottery.freeRefreshTime)
                val nowFreeDrawTime = getDiscountRefreshTime(now)
                if (lastFreeDrawTime - nowFreeDrawTime > 24 * 3600 * 1000) {
                    eachLottery.freeRefreshTime = now
                    eachLottery.restFreeDrawTimes = pcs.basicProtoCache.lowDropFreeNum
                    eachLottery.lastFreeDrawTime = 0
                }
            }

            // 高级
            if (eachLottery.protoId == 2) {
                // 刷新cd
                if (now - eachLottery.lastFreeDrawTime > pcs.basicProtoCache.highDropFreeCd * 1000) {
                    eachLottery.restFreeDrawTimes = 1
                    eachLottery.lastFreeDrawTime = 0
                }
            }

            // 活动

            // 是否到每天6点定时刷新折扣 和普通的次数
            val lastRefreshDiscount = getDiscountRefreshTime(eachLottery.lastUseDiscountTime)
            val nowRefreshDiscount = getDiscountRefreshTime(now)
            if (nowRefreshDiscount - lastRefreshDiscount > 24 * 3600 * 1000) {
                eachLottery.lastUseDiscountTime = now
                eachLottery.discountToday = 1
            }
        }
    }
}

// 抽卡
class Lottery(
    var id: Long,
    var oldHash: Int,
    var protoId: Int,            // 模板id
    var discountToday: Int,     // 今天连抽10次的折扣次数
    var activityOverTime: Long, // 活动结束时间
    var playerId: Long,         // 玩家id
    var restFreeDrawTimes: Int, // 这一轮剩余的免费抽次数
    var thisRoundDrawSum: Long, // 这一轮总共抽了多少次
    var drawSum: Long,          // 这个角色总共抽了多少次
    var lastUseDiscountTime: Long, // 上次使用折扣的时间  6点更新
    var freeRefreshTime: Long,   // 上一次刷新的时间 6点更新
    var lastFreeDrawTime: Long       // 上次免费抽卡时间  用作 cd
) : EntityWrapper<LotteryEntity> {

    constructor() : this(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = LotteryPK(playerId, id)

    override fun toEntity(): LotteryEntity {
        return LotteryEntity(
            id,
            protoId,
            discountToday,
            activityOverTime,
            playerId,
            restFreeDrawTimes,
            thisRoundDrawSum,
            drawSum,
            lastUseDiscountTime,
            freeRefreshTime,
            lastFreeDrawTime
        )
    }

    override fun wrap(entity: LotteryEntity) {
        id = entity.id
        oldHash = entity.hashCode()
        protoId = entity.protoId
        discountToday = entity.discountToday
        activityOverTime = entity.activityOverTime
        playerId = entity.playerId
        restFreeDrawTimes = entity.restFreeDrawTimes
        thisRoundDrawSum = entity.thisRoundDrawSum
        drawSum = entity.drawSum
        lastUseDiscountTime = entity.lastUseDiscountTime
        freeRefreshTime = entity.freeRefreshTime
        lastFreeDrawTime = entity.lastFreeDrawTime
    }

}







