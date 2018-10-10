package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.checkDecree
import com.point18.slg2d.world.common.getResearchEffPlusRate
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import java.util.*
import java.util.Arrays.asList

// 打召唤boss
class WalkCallBoss : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckHaveHero or CheckNoSolider
    }

    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, gotoX, gotoY)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        // 是否已有行军部队判定
        val myGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPlayerId(wp.playerId)
        for (group in myGroups) {
            if (group.runningState != Running) {
                continue
            }
            if (group.gotoRunType != WalkCallBoss) {
                continue
            }
            return ResultCode.HAVE_FORCE_FIGHT_BOSS.code
        }

        // 目标格子判断
        val callBoss = areaCache.callBossCache.findCallBossByXy(wp.gotoX, wp.gotoY)
        if (callBoss == null) {
            return ResultCode.NO_FIGHT_BOSS.code
        }
        val bossId = callBoss.bossId

        //魔物配置校验
        val monsterProto = pcs.monsterProtoCache.findMonsterProto(bossId)
        if (monsterProto == null) {
            normalLog.error("魔物配置不存在:%d", bossId)
            return ResultCode.NO_PROTO.code
        }

        //玩家校验
        val player = areaCache.playerCache.findPlayerById(wp.playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", wp.playerId)
            return ResultCode.NO_PLAYER.code
        }

        //玩家联盟校验
        if (player.allianceId == 0L) {
            return ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
        }

        //阵型判定
        val armyPlan = areaCache.armyPlanCache.findArmyPlan(
            player.id,
            com.point18.slg2d.common.constg.MonsterFight,
            monsterProto.mainId
        )
        if (armyPlan == null) {
            return ResultCode.NO_SET_ARMY_PLAN.code
        }

        //行动力校验
        var costDecree = monsterProto.energy
        costDecree = (costDecree *
                getResearchEffPlusRate(
                    areaCache,
                    NO_FILTER,
                    player,
                    ActivityValueCostAdd
                ) *
                getResearchEffPlusRate(
                    areaCache,
                    NO_FILTER,
                    player,
                    HunterRaceCostReduce + monsterProto.race - 1
                ) *
                getResearchEffPlusRate(
                    areaCache,
                    NO_FILTER,
                    player,
                    HunterLvCostReduce + monsterProto.level - 1
                )).toInt()

        val costRes = asList(ResVo(RES_DECREE, NOT_PROPS_SUB_TYPE, costDecree.toLong()))

        val checkResult = checkDecree(areaCache, player, costRes)

        return checkResult.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        return ResultCode.SUCCESS.code
    }
}



