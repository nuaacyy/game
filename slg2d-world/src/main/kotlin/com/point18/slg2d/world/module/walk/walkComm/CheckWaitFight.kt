package com.point18.slg2d.world.module.walk.walkComm

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.IBossInfo
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.fightdomain.createFightDataByArmyPlan
import com.point18.slg2d.world.module.fightdomain.createFightDataByMonster
import com.point18.slg2d.world.module.walk.fightStrategy.bossFight
import java.util.*
import java.util.Arrays.asList

val checkBossFightDeal = CheckBossFightDeal()

class CheckBossFightDeal {
    fun checkFight(areaCache: AreaCache, x: Int, y: Int, runType: Int) {
        val commonBossInfo = areaCache.commonBossCache.findCommonBossByXY(x, y)
        val callBossInfo = areaCache.callBossCache.findCallBossByXy(x, y)
        val activityBossInfo = areaCache.activityBossCache.findActivityBossByXy(x, y)

        // 行为判断
        val action: Int
        val bossInfo: IBossInfo = when {
            commonBossInfo != null -> {
                action = PVE_FIGHT_COMMON_BOSS_ACTION
                commonBossInfo
            }
            callBossInfo != null -> {
                action = PVE_FIGHT_CALL_BOSS_ACTION
                callBossInfo
            }
            activityBossInfo != null -> {
                action = PVE_FIGHT_ACTIVITY_BOSS_ACTION
                activityBossInfo
            }
            else -> return
        }

        //请求战斗
        val waitFightGroups =
            areaCache.walkForceGroupCache.walkForceGroupMapByPosAndState.findByKey(
                x,
                y,
                com.point18.slg2d.common.constg.WaitFight
            )
                ?: return
        if (waitFightGroups.isEmpty()) {
            return
        }
        val atkGroup = waitFightGroups[0]

        val monsterProto = pcs.monsterProtoCache.findMonsterProto(bossInfo.getBossProtoId()) ?: return

        val armyPlan =
            areaCache.armyPlanCache.findArmyPlan(
                atkGroup.mainPlayerId,
                com.point18.slg2d.common.constg.MonsterFight,
                monsterProto.mainId
            )
                ?: return

        val player = areaCache.playerCache.findPlayerById(atkGroup.mainPlayerId) ?: return

        //行动力扣除
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

        costDecree(
            areaCache, player, LinkedList(
                asList(
                    ResVo(RES_DECREE, NOT_PROPS_SUB_TYPE, costDecree.toLong())
                )
            )
        )

        val atkFightData = createFightDataByArmyPlan(areaCache, armyPlan)
        val defFightData = createFightDataByMonster(monsterProto, bossInfo.getCurrentHp())
        bossFight.doBossFight(
            areaCache,
            action,
            runType,
            x,
            y,
            atkGroup,
            null,
            atkFightData,
            defFightData,
            hashMapOf(Pair(CostEnergy, costDecree)),
            {
                //送回家
                if (atkGroup.runningState != Running) {
                    goHome(areaCache, x, y, atkGroup, true)
                }

                // 刷新地块
                noticeCellUpdate(areaCache, x, y)

                //再次调用本方法去检测战斗部队
                checkFight(areaCache, x, y, runType)
            },
            {
                //返还行动力
                addDecree(areaCache, player, costDecree, false)

                //送回家
                if (atkGroup.runningState != Running) {
                    goHome(areaCache, x, y, atkGroup, false)
                }

                //发送来迟邮件
                val mailInfo = MailInfo(
                    TEXT_READ_LAN,
                    SYSTEM_MAIL,
                    LinkedList(),
                    TARGET_DISAPPEAR_CONTENT,
                    LinkedList(asList(WALK_PARAS + runType.toString()))
                )
                sendMail(areaCache, atkGroup.mainPlayerId, mailInfo)

                //再次调用本方法去检测战斗部队
                checkFight(areaCache, x, y, runType)
            }
        )
    }
}
