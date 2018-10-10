package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroup
import com.point18.slg2d.world.module.fightdomain.createFightDataByPlayer
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.module.walk.fightStrategy.soliderFight
import java.util.*

class DealFightPlayerOnCastleCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val playerId = group.mainPlayerId
        val gotoX = walkLineInfo.marchAimsX
        val gotoY = walkLineInfo.marchAimsY

        // 判断两个人是否不同公会
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到行军组的玩家信息:%d", playerId)
            return ResultCode.PARAMETER_ERROR.code
        }

        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle == null) {
            normalLog.error("找不到坐标{%d,%d}对应的玩家城信息", gotoX, gotoY)
            return ResultCode.PARAMETER_ERROR.code
        }

        val targetPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        if (targetPlayer == null) {
            normalLog.error("找不到玩家城对应的玩家信息，城池Id:%d", castle.playerId)
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId != 0L && player.allianceId == targetPlayer.allianceId) {
            // 两个人同一个公会
            return ResultCode.IN_SAME_ALLIANCE.code
        }

        val (isDefHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(targetPlayer.id)
        if (isDefHaveCover) {
            return ResultCode.HAVE_DEF_COVER_BUFF_WHEN_FIGHT.code
        }

        val (isAtkHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(player.id)
        if (isAtkHaveCover) {
            return ResultCode.HAVE_ATK_COVER_BUFF_WHEN_FIGHT.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle == null) {
            normalLog.error("行军线目的地的城池(%d,%d)不存在", posX, posY)
            return
        }

        //驻守部队============================================
        val fightStationForceResult = fightWithStationForce(areaCache, group, walkLineInfo)
        if (fightStationForceResult == FIGHT_RESULT_LOSE) {
            return
        }

        //增援部队============================================
        //玩家部队============================================
        val fightCityForceResult = fightWithCityForce(areaCache, group, walkLineInfo, castle)

        // 刷新地图上的城堡状态
        if (fightCityForceResult == FIGHT_RESULT_WIN) {
            // 变成失火状态
            updateCastleState(areaCache, castle, CASTLE_FIRE, pcs.basicProtoCache.cityFireTime)
        } else if (fightCityForceResult == FIGHT_RESULT_LOSE) {
            // 变成冒烟状态
            updateCastleState(areaCache, castle, CASTLE_SMOKE, pcs.basicProtoCache.citySmokeTime)
        }

        // 更新大地图信息
        noticeCellUpdate(areaCache, posX, posY)
    }

    //和驻扎部队战斗
    private fun fightWithStationForce(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val stationGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
            posX,
            posY,
            Stationed
        )
        for (stationGroup in stationGroups) {
            val atkFightData = createFightDataByForceGroup(areaCache, group)
            val defFightData = createFightDataByForceGroup(areaCache, stationGroup)
            val atkForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
            val defForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(stationGroup.id)

            val fightResultData = soliderFight.doSoliderFight(
                areaCache,
                PVP_FIGHT_GROUP_ACTION,
                WalkFightPlayer,
                FIGHT_PLAYER_REPORT,
                group.massId != 0L,
                posX,
                posY,
                atkForceList,
                defForceList,
                atkFightData,
                defFightData
            )
            val fightResult = fightResultData.fightResult

            if (fightResult == FIGHT_RESULT_LOSE) {
                atkFightData.checkPrison(
                    areaCache,
                    group,
                    defFightData.playerId
                )
                //攻击方回城
                goHome(areaCache, posX, posY, group)
                return FIGHT_RESULT_LOSE
            }
            //防守驻防部队回城
            goHome(areaCache, posX, posY, stationGroup)
        }

        return FIGHT_RESULT_WIN
    }

    //和本城部队战斗，包括增援与玩家自身部队
    private fun fightWithCityForce(
        areaCache: AreaCache,
        group: WalkForceGroup,
        walkLineInfo: Walk,
        castle: Castle
    ): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val atkFightData = createFightDataByForceGroup(areaCache, group)
        val cityFightData = createFightDataByPlayer(areaCache, castle.playerId)
        val atkForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        val fightResultData = soliderFight.doSoliderFight(
            areaCache,
            PVP_FIGHT_PLAYER_ACTION,
            WalkFightPlayer,
            FIGHT_PLAYER_REPORT,
            group.massId != 0L,
            posX,
            posY,
            atkForceList,
            LinkedList(),
            atkFightData,
            cityFightData
        )
        val fightResult = fightResultData.fightResult

        val reinforceGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Reinforce)

        for (reinforceGroup in reinforceGroups) {
            if (fightResult == FIGHT_RESULT_LOSE && reinforceGroup.checkHaveSolider(areaCache)) {
                //防守胜利且增援部队还有兵
                continue
            }
            //增援部队防守失败，回城
            goHome(areaCache, posX, posY, reinforceGroup)
        }

        if (fightResult == FIGHT_RESULT_LOSE) {
            atkFightData.checkPrison(areaCache, group, cityFightData.playerId)
        } else {
            cityFightData.checkPrison(areaCache, null, atkFightData.playerId)
        }

        goHome(areaCache, posX, posY, group)

        return fightResult
    }

    //更新城池状态
    private fun updateCastleState(areaCache: AreaCache, castle: Castle, state: Int, lastTime: Int) {
        castle.castleState = state // 变成失火状态
        syncData2Home(
            areaCache,
            castle.playerId,
            CastleStateSync,
            castle.castleState.toString()
        )
        val castleStatusEndTime = getNowTime() + lastTime * 1000 //修改持续时间
        areaCache.castleCache.updateCastleStateEndTime(castle, castleStatusEndTime)
    }
}

