package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.event.SetArmyPlanEvent
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import pb4client.HeroPos
import pb4client.SetArmyPlan
import pb4client.SetArmyPlanRt
import java.util.*

// 设置英雄战预设部队
class SetArmyPlanDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val setMsg = msg as SetArmyPlan
        val rt = this.setArmyPlan(session, setMsg.bigTarget, setMsg.smallTarget, LinkedList(setMsg.heroInfoList))
        session.sendMsg(MsgType.SetArmyPlan_1266, rt.build())
    }

    private fun setArmyPlan(
        session: PlayerSession,
        bigTarget: Int,
        smallTarget: Int,
        heroInfo: List<HeroPos>
    ): SetArmyPlanRt.Builder {
        val rtBuilder = SetArmyPlanRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        //校验
        when (bigTarget) {
            com.point18.slg2d.common.constg.JjcFight -> {
                when (smallTarget) {
                    JjcAtk -> {

                    }
                    JjcDef -> {
                        // todo
                    }
                    else -> {
                        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                        return rtBuilder
                    }
                }
            }
            MissionFight -> {
            }
            MonsterFight -> {
                val monster = pcs.monsterProtoCache.checkMainIdExist(smallTarget)
                if (!monster) {
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
            }
            else -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        }

        if (heroInfo.isEmpty() || heroInfo.count() > pcs.basicProtoCache.monsterHeroMax) {
            // 数量超限
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val heroMap = hashMapOf<Int, Long>()
        for (heroPos in heroInfo) {
            val pos = heroPos.pos
            val heroId = heroPos.heroId
            if (pos < 1 || pos > 9) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            val hero = areaCache.heroCache.findHeroById(playerId, heroId)
            if (hero == null) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            if (heroMap.containsKey(pos)) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            heroMap[pos] = heroId
        }

        val armyPlan = areaCache.armyPlanCache.findArmyPlan(playerId, bigTarget, smallTarget)
        if (armyPlan != null) {
            armyPlan.heroMap = heroMap
        } else {
            areaCache.armyPlanCache.createArmyPlan(playerId, bigTarget, smallTarget, heroMap)
        }

        wpm.es.fire(areaCache, playerId, SET_ARMY_PLAN, SetArmyPlanEvent())

        return rtBuilder
    }
}



