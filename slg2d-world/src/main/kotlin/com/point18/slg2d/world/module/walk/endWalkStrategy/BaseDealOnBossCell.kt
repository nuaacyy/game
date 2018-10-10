package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.MonsterProto
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.checkDecree
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.checkBossFightDeal
import java.util.Arrays.asList

//魔物的通用处理
abstract class BaseDealOnBossCell : ICellDeal {
    fun walkBossCheck(areaCache: AreaCache, group: WalkForceGroup, bossInfo: IBossInfo?): Int {
        if (bossInfo == null || bossInfo.getCurrentHp() <= 0) {
            return ResultCode.NO_FIGHT_BOSS.code
        }
        val bossId = bossInfo.getBossProtoId()

        //魔物配置校验
        val monsterProto = pcs.monsterProtoCache.findMonsterProto(bossId)
        if (monsterProto == null) {
            normalLog.error("魔物配置不存在:%d", bossId)
            return ResultCode.NO_PROTO.code
        }

        //玩家校验
        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", group.mainPlayerId)
            return ResultCode.NO_PLAYER.code
        }

        //玩家联盟校验
        if (player.allianceId == 0L) {
            return ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
        }

        //阵型判定
        val armyPlan = areaCache.armyPlanCache.findArmyPlan(player.id, MonsterFight, monsterProto.mainId)
        if (armyPlan == null) {
            return ResultCode.NO_SET_ARMY_PLAN.code
        }

        //行动力校验
        return checkFightBossDecree(areaCache, player, monsterProto).code
    }

    fun walkBossDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val waitFightGroups =
            areaCache.walkForceGroupCache.walkForceGroupMapByPosAndState.findByKey(posX, posY, WaitFight)
        if (waitFightGroups != null && waitFightGroups.isNotEmpty()) {
            //修改行军组状态
            areaCache.walkForceGroupCache.changeGroupState(group, WaitFight)
            return
        }

        //修改行军组状态
        areaCache.walkForceGroupCache.changeGroupState(group, WaitFight)

        checkBossFightDeal.checkFight(areaCache, posX, posY, walkLineInfo.marchState)
    }

    //行动力校验
    private fun checkFightBossDecree(areaCache: AreaCache, player: Player, monsterProto: MonsterProto): ResultCode {
        var costDecree = monsterProto.energy
        costDecree = (costDecree * (1.0 - getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            ActivityValueCostAdd
        ) / 10000.0) * (1.0 - getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            HunterRaceCostReduce + monsterProto.race - 1
        ) / 10000.0) * (1.0 - getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            HunterLvCostReduce + monsterProto.level - 1
        ) / 10000.0)).toInt()

        return checkDecree(
            areaCache, player,
            asList(
                ResVo(RES_DECREE, NOT_PROPS_SUB_TYPE, costDecree.toLong())
            )
        )
    }
}