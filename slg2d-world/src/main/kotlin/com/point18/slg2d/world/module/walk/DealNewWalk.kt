package com.point18.slg2d.world.module.walk

import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.WalkElement
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.world.module.walk.walkComm.createMarch
import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.common.calWalkTime
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.world.common.updateWarnWithPos
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.Walk
import pb4client.WalkRt
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 远征
class StartWalkDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.dealMarch(session, msg as Walk)
        session.sendMsg(MsgType.Walk_15, rt.build())
    }

    private fun dealMarch(session: PlayerSession, march: Walk): WalkRt.Builder {
        val rtBuilder = WalkRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code // 使rt为1，具体错误码放到errorCode中
        rtBuilder.errorCode = ResultCode.SUCCESS.code

        var gotoX = march.aimsX
        var gotoY = march.aimsY
        val runType = march.runType
        val targetId = march.targetId
        val areaCache = session.areaCache
        val playerId = session.playerId

        // 刷新下资源
        val player = session.player
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.lzWarn { "找不到玩家城数据:${player.focusCastleId}" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val startX = castle.x
        val startY = castle.y

        if (runType == WalkTransport || runType == WalkScout) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val heroIds = LinkedList(march.herosList)
        for (heroId in march.herosList) {
            val hero = session.areaCache.heroCache.findHeroById(session.playerId, heroId)
            if (hero == null) {
                rtBuilder.errorCode = ResultCode.NO_HERO.code
                return rtBuilder
            }
            if (hero.posState != IN_CITY) {
                rtBuilder.errorCode = ResultCode.HERO_POS_STATE_ERROR.code
                return rtBuilder
            }
            if (hero.mainHeroState != NO_MAIN_HERO && hero.mainHeroState != MAIN_HERO) {
                rtBuilder.errorCode = ResultCode.HERO_POS_STATE_ERROR.code
                return rtBuilder
            }
        }
        val soliderMap = hashMapOf<Int, Int>()
        for (sInfo in march.solidersList) {
            if (sInfo.num <= 0) {
                continue
            }
            soliderMap[sInfo.protoId] = sInfo.num
        }

        // 根据不同的出征类型验证各自的特殊需求
        val newWalkDeal = walkM.walkDeals[runType]
        if (newWalkDeal == null) {
            rtBuilder.errorCode = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 查找目的地坐标
        val rst = newWalkDeal.getPosByTarget(areaCache, targetId, gotoX, gotoY)
        if (rst.result != ResultCode.SUCCESS.code) {
            rtBuilder.errorCode = rst.result
            return rtBuilder
        }
        gotoX = rst.posX
        gotoY = rst.posY

        // 读取表
        if (gotoX >= MAX_MAP_SIZE || gotoY >= MAX_MAP_SIZE) {
            rtBuilder.errorCode = ResultCode.MAP_PROTO_NOT_EXIST.code
            return rtBuilder
        }

        // 出征队列上限校验
        val forces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
        if (forces.count() >= getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                ResearchEffectWalkQueueAdd
            )
        ) {
            rtBuilder.errorCode = ResultCode.WalkQueueUpLimit.code
            return rtBuilder
        }

        val dealRt = dealWalk(
            areaCache,
            playerId,
            runType,
            startX,
            startY,
            targetId,
            gotoX,
            gotoY,
            heroIds,
            soliderMap,
            "",
            LinkedList()
        )
        rtBuilder.errorCode = dealRt.rt
        if (dealRt.limit != -1) {
            //limit的默认值为-1，如果被修改过，则获取他的值给WalkRt的limit
            rtBuilder.limit = dealRt.limit
        }
        rtBuilder.groupId = dealRt.groupId
        return rtBuilder
    }
}

data class DealWalkRt(
    var rt: Int,
    var groupId: Long,
    var limit: Int = -1
)

fun dealWalk(
    areaCache: AreaCache,
    playerId: Long,
    runType: Int,
    startX: Int,
    startY: Int,
    targetId: Long,
    gotoX: Int,
    gotoY: Int,
    heroIds: LinkedList<Long>,
    soliderMap: HashMap<Int, Int>,
    resFromInfo: String = "",
    realRes: LinkedList<ResVo> = LinkedList()
): DealWalkRt {
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("找不到玩家信息:$playerId")
        return DealWalkRt(ResultCode.NO_PLAYER.code, 0)
    }

    val newWalkDeal = walkM.walkDeals[runType]
    if (newWalkDeal == null) {
        return DealWalkRt(ResultCode.PARAMETER_ERROR.code, 0)
    }

    // 出发检测
    val wp = WalkParam(
        playerId,
        startX,
        startY,
        targetId,
        gotoX,
        gotoY,
        WalkElement(heroIds, soliderMap)
    )
    var result = newWalkDeal.startCheck(areaCache, wp)
    if (result != ResultCode.SUCCESS.code) {
        return DealWalkRt(result, 0)
    }

    // 其他独立的检测
    val rs = IWalkDeal.WalkStartCheckResult()
    result = newWalkDeal.walkStartCheck(areaCache, wp, rs)
    if (result != ResultCode.SUCCESS.code) {
        if (result == ResultCode.ReinforceSoliderUpLimit.code) {
            return DealWalkRt(result, 0, rs.limit)
        }
        return DealWalkRt(result, 0)
    }

    val walkTimeRt = calWalkTime(
        areaCache,
        playerId,
        runType,
        soliderMap,
        startX,
        startY,
        gotoX,
        gotoY
    )

    val nowTime = getNowTime()
    val arriveTime = nowTime + walkTimeRt.walkTime * 1000

    // 添加行军记录
    val marchRt = createMarch(
        areaCache,
        playerId,
        heroIds,
        soliderMap,
        gotoX,
        gotoY,
        arriveTime,
        runType,
        startX,
        startY,
        walkTimeRt.walkSpeed,
        resFromInfo,
        realRes
    )
    val walk = marchRt.walk
    val group = marchRt.group

    newWalkDeal.walkStartDeal(areaCache, walk, targetId)

    // 通知新增行军组信息
    noticeSelfWalkForceGroup(areaCache, Add, group)

    // 刷新预警
    updateWarnWithPos(areaCache, walk.marchAimsX, walk.marchAimsY)

    // 设置英雄状态
    val heroChangeMsg = createValueChgNotifier()
    for (heroId in heroIds) {
        val hero = areaCache.heroCache.findHeroById(playerId, heroId)
        if (hero == null) {
            normalLog.error("找不到玩家英雄数据:$heroId")
            continue
        }
        hero.posState = OUT_CITY // 修改英雄状态
        heroChangeMsg.append(hero.id, HERO_POS_STATE, (OUT_CITY).toLong())
    }

    fetchOnlinePlayerSession(areaCache, playerId)?.let {
        heroChangeMsg.notice(it)
    }

    return DealWalkRt(ResultCode.SUCCESS.code, group.id)
}


