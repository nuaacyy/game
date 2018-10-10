package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.common.addHeroExp
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.getResearchEffPlusRate
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.RewardInfoForReport
import com.point18.slg2d.world.module.fightdomain.createRewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.soliderBattle.fightdomain.SoliderFightInfoData
import java.util.*
import java.util.Arrays.asList

//PVP攻打玩家行军组战斗流程
class PvpFightGroupAction(var action: Int) : BaseSoliderFightDeal() {
    override fun dealAfterOneFight(
        areaCache: AreaCache,
        atkForceList: List<WalkForce>,
        defForceList: List<WalkForce>,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        isMass: Boolean,
        posX: Int,
        posY: Int,
        detailFightInfo: SoliderFightInfoData
    ): Boolean {
        //计算战报
        val atkInfoAfterFight = atkFightData.calSoliderAfterFight(areaCache, pcs.basicProtoCache.pvpAttackerDieRate)
        val defInfoAfterFight = defFightData.calSoliderAfterFight(areaCache, pcs.basicProtoCache.pvpAttackerDieRate)

        //分配战斗后的击杀击伤
        divideSoliderAfterFight(atkFightData, defFightData, atkInfoAfterFight, defInfoAfterFight)

        val rewardMap = hashMapOf<Long, RewardInfoForReport>()
        //填充奖励结构
        for (soliderData in atkFightData.soliderDataList) {
            rewardMap[soliderData.playerId] = createRewardInfoForReport()
        }
        for (soliderData in defFightData.soliderDataList) {
            rewardMap[soliderData.playerId] = createRewardInfoForReport()
        }

        //计算攻击方
        val atkHeroMap = hashMapOf<Long, Int>() //用于实际增加英雄经验
        for ((_, killData) in atkInfoAfterFight.killedSoliderDataMap) {
            var totalKingExp = 0.0
            var totalHeroExp = 0.0
            for ((killId, killNum) in killData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[killId]
                if (soliderProto == null) {
                    normalLog.error("士兵配置不存在:%d", killId)
                    continue
                }
                totalKingExp += soliderProto.lordExp * killNum
                totalHeroExp += soliderProto.heroExp * killNum
            }
            val rewardInfo = rewardMap[killData.playerId]
            if (rewardInfo == null) {
                normalLog.error("战斗后的击杀数据中的玩家不存在；5d", killData.playerId)
                continue
            }
            rewardInfo.kingExp = totalKingExp.toInt()
            if (killData.playerId == atkFightData.playerId && atkFightData.heroList.count() > 0) {
                val averageHeroExp = totalHeroExp / (atkFightData.heroList.count())
                for (hero in atkFightData.heroList) {
                    rewardInfo.heroExp[hero.protoId] = averageHeroExp.toInt()
                    atkHeroMap[hero.id] = averageHeroExp.toInt()
                }
            }
        }

        //计算防守方
        val defHeroMap = hashMapOf<Long, Int>() //用于实际增加英雄经验
        for ((_, killData) in defInfoAfterFight.killedSoliderDataMap) {
            var totalKingExp = 0.0
            var totalHeroExp = 0.0
            for ((killId, killNum) in killData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[killId]
                if (soliderProto == null) {
                    normalLog.error("士兵配置不存在:%d", killId)
                    continue
                }
                totalKingExp += soliderProto.lordExp * killNum
                totalHeroExp += soliderProto.heroExp * killNum
            }
            val rewardInfo = rewardMap[killData.playerId]
            if (rewardInfo == null) {
                normalLog.error("战斗后的击杀数据中的玩家不存在；5d", killData.playerId)
                continue
            }
            rewardInfo.kingExp = totalKingExp.toInt()
            if (killData.playerId == defFightData.playerId && defFightData.heroList.count() > 0) {
                val averageHeroExp = totalHeroExp / (defFightData.heroList.count())
                for (hero in defFightData.heroList) {
                    rewardInfo.heroExp[hero.protoId] = averageHeroExp.toInt()
                    defHeroMap[hero.id] = averageHeroExp.toInt()
                }
            }
        }

        //结算战斗奖励
        for ((playerId, reward) in rewardMap) {
            val action = ACTION_FIGHT_CASTLE
            val player = areaCache.playerCache.findPlayerById(playerId)
            if (player == null) {
                normalLog.error("找不到玩家数据:%d", playerId)
                continue
            }
            reward.kingExp = (reward.kingExp *
                    getResearchEffPlusRate(
                        areaCache,
                        NO_FILTER,
                        player,
                        KingExpAdd
                    )).toInt()

            if (reward.kingExp > 0) {
                addResToHome(
                    areaCache,
                    action,
                    player.id,
                    asList(ResVo(RES_KING_EXP, NOT_PROPS_SUB_TYPE, reward.kingExp.toLong()))
                )
            }
            if (playerId == atkFightData.playerId) {
                //AddResToHome(areaCache, action, player, reward.resVos)
                for ((heroId, addExp) in atkHeroMap) {
                    addHeroExp(areaCache, playerId, heroId, addExp)
                }
            } else if (playerId == defFightData.playerId) {
                //CostRes(action, player, reward.resVos, areaCache)
                for ((heroId, addExp) in defHeroMap) {
                    addHeroExp(areaCache, playerId, heroId, addExp)
                }
            }
        }

        //生成战报
        val reportInfo = ReportInfo(
            areaCache,
            posX,
            posY,
            atkFightData,
            defFightData,
            null, null,
            toJson(detailFightInfo).toByteArray(Charsets.UTF_8)
        )
        var wonderId = 0
        if (runType == WalkOccupyWonder) {
            val findRst = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
            val wonderProto = findRst.wonderLocationProto
            if (wonderProto == null) {
                normalLog.error("（%d,%d）的奇观数据不存在", posX, posY)
                return false
            }
            wonderId = wonderProto.id
        }
        reportInfo.genFightPlayerReport(
            rewardMap,
            atkInfoAfterFight,
            defInfoAfterFight,
            isMass,
            defFightData.soliderDataList.size > 1,
            areaCache.castleCache.findCastleByXy(posX, posY) != null,
            wonderId,
            detailFightInfo.fightResult,
            detailFightInfo.fightResult == FIGHT_RESULT_WIN
                    && defFightData.checkAllWipedOut() && defFightData.checkHaveMainHero(areaCache),
            detailFightInfo.fightResult == FIGHT_RESULT_LOSE
                    && atkFightData.checkAllWipedOut() && atkFightData.checkHaveMainHero(areaCache)
        )

        if (detailFightInfo.fightResult == FIGHT_RESULT_LOSE) {
            val castle = areaCache.castleCache.findCastleByXy(posX, posY)
            if (castle != null) {
                //驻防部队防守胜利时，给城池玩家发送战报
                val stationReportInfo = ReportInfo(
                    areaCache,
                    posX,
                    posY,
                    atkFightData,
                    defFightData,
                    null, null,
                    "".toByteArray(Charsets.UTF_8)
                )
                stationReportInfo.genStationForceDefSuccessReport(
                    atkFightData.playerId,
                    defFightData.playerId,
                    castle.playerId
                )
            }
        }

        //更新部队数据（全部是行军组士兵）
        atkInfoAfterFight.refreshForceList(areaCache, atkForceList)
        defInfoAfterFight.refreshForceList(areaCache, defForceList)

        //统计数据
        statisticSoliderData(areaCache, runType, atkInfoAfterFight)
        statisticSoliderData(areaCache, runType, defInfoAfterFight)

        statisticFightData(areaCache, atkFightData, defFightData, detailFightInfo.fightResult, isMass)

        return false
    }
}
