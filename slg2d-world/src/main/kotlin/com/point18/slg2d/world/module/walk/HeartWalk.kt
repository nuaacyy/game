package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.updateWarnWithPos
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.walkComm.deleteWalkOnMap
import com.point18.slg2d.world.module.walk.walkComm.goHome
import xyz.ariane.util.lzDebug
import java.util.*
import java.util.Arrays.asList

class HeartWalk : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        val allWalk = cache.walkCache.findAllWalkForTimeOver()

        for (walk in allWalk) {
            dealWalkFinish(cache, walk)
        }
    }
}

fun sendHome(areaCache: AreaCache, walk: Walk) {
    val walkForceGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
    if (walkForceGroup == null) {
        assert(false) { "找不到行军主体: ${walk.walkForceGroupId}" }
        return
    }

    deleteWalkOnMap(areaCache, walk)

    goHome(areaCache, walk.marchAimsX, walk.marchAimsY, walkForceGroup)
}

fun dealWalkFinish(areaCache: AreaCache, walk: Walk) {

    // 行军结束处理
    val cellDealMap = walkM.cellDeals[walk.marchState]
    if (cellDealMap == null) {
        normalLog.error("错误的行军类型: ${walk.marchState}")
        sendHome(areaCache, walk)
        return
    }

    val posX = walk.marchAimsX
    val posY = walk.marchAimsY

    val targetCellType = areaCache.mapCellCache.getCellType(posX, posY)
    val cellDeal = cellDealMap[targetCellType]
    if (cellDeal == null) {
        normalLog.lzDebug { "木有在${targetCellType}地块上，对应${walk.marchState}的行军处理" }

        //发送目标点已消失邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            TARGET_DISAPPEAR_CONTENT,
            LinkedList(asList(WALK_PARAS + walk.marchState.toString()))
        )
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walk.walkForceGroupId)
        for (force in forces) {
            sendMail(areaCache, force.playerId, mailInfo)
        }

        sendHome(areaCache, walk)
        return
    }

    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
    if (group == null) {
        normalLog.error("处理行军结束时，行军线对应的行军组不存在，行军线Id:${walk.id}")
        return
    }

    // 出征到了.继续验证是否符合.不符合的话返回
    val result = cellDeal.walkFinishCheck(areaCache, group, walk)
    if (result != ResultCode.SUCCESS.code) {
        var mailContent = ""
        val mainContentParas = LinkedList<String>()
        //不同的检测结果，额外做些处理，如发送邮件
        when (result) {
            ResultCode.NO_FIGHT_BOSS.code -> {
                //木有魔物
                mailContent = TARGET_DISAPPEAR_CONTENT
                mainContentParas.add(WALK_PARAS + walk.marchState.toString())
            }
            ResultCode.ALLIANCE_PLAYER_NO_JOIN.code,
            ResultCode.IN_SAME_ALLIANCE.code -> {
                //联盟关系发生变化
                mailContent = ALLIANCE_RELATION_CHANGE_CONTENT
                mainContentParas.add(WALK_PARAS + walk.marchState.toString())
            }
            ResultCode.OVER_LIMIT_STATION.code ->
                //已有驻扎部队
                mailContent = HAVE_REINFORCE_CONTENT
            ResultCode.HAVE_DEF_COVER_BUFF_WHEN_FIGHT.code ->
                //战斗时对方有防护罩
                mailContent = TARGET_UNABLE_ATTACK_CONTENT
            ResultCode.HAVE_ATK_COVER_BUFF_WHEN_FIGHT.code ->
                //战斗时自身有防护罩
                mailContent = SELF_HAVE_COVER_CONTENT
            ResultCode.HAVE_FANZHENCHA_BUFF.code,
            ResultCode.HAVE_DEF_COVER_BUFF_WHEN_SCOUT.code -> {
                //侦查时有防护罩/反侦察科技
                val scoutReportInfo = ReportInfo(areaCache, posX, posY, null, null, null, null, "".toByteArray())
                val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walk.walkForceGroupId)
                for (force in forces) {
                    scoutReportInfo.genScoutFailedReport(force.playerId, targetCellType, result)
                    val castle = areaCache.castleCache.findCastleByXy(posX, posY)
                    if (castle != null) {
                        scoutReportInfo.genBeScoutReport(force.playerId, castle.playerId, targetCellType, result)
                    }
                }
            }
            ResultCode.HAVE_OCCUPY_WONDER.code -> {
                //已占领奇观
                mailContent = EXCHANGE_WONDER_DEF_FAIL
            }
            ResultCode.OTHER_ALLIANCE_PLAYER_IN_FARM.code -> {
                //其他玩家正在采集中
                mailContent = RES_BE_FARMING_CONTENT
            }
        }
        if (mailContent != "") {
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                SYSTEM_MAIL,
                LinkedList(),
                mailContent,
                mainContentParas
            )
            val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walk.walkForceGroupId)
            for (force in forces) {
                sendMail(areaCache, force.playerId, mailInfo)
            }
        }

        sendHome(areaCache, walk)
        return
    }


    areaCache.walkForceGroupCache.updateWalkForceGroupPos(group, walk.marchAimsX, walk.marchAimsY)

    deleteWalkOnMap(areaCache, walk)

    // 部队完成行军处理
    cellDeal.walkFinishDeal(areaCache, group, walk)

    // 刷新预警
    updateWarnWithPos(areaCache, walk.marchAimsX, walk.marchAimsY)
}
