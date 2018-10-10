package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.GetArmyPlan
import pb4client.GetArmyPlanRt
import pb4client.HeroPos

// 查询英雄战预设部队
class GetArmyPlanDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val getMsg = msg as GetArmyPlan
        val rt = this.getArmyPlan(session, getMsg.bigTarget, getMsg.smallTarget)

        session.sendMsg(MsgType.GetArmyPlan_1267, rt.build())
    }

    private fun getArmyPlan(session: PlayerSession, bigTarget: Int, smallTarget: Int): GetArmyPlanRt.Builder {
        val rtBuilder = GetArmyPlanRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        //校验
        when (bigTarget) {
            JjcFight -> {
                when (smallTarget) {
                    JjcAtk -> {
                    }
                    JjcDef -> {
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

        //取阵容数据
        val areaCache = session.areaCache
        val playerId = session.playerId
        val armyPlan = areaCache.armyPlanCache.findArmyPlan(playerId, bigTarget, smallTarget)
        if (armyPlan == null) {
            return rtBuilder
        }

        for ((pos, heroId) in armyPlan.heroMap) {
            val posBuilder = HeroPos.newBuilder()
            posBuilder.pos = pos
            posBuilder.heroId = heroId
            rtBuilder.addHeroInfo(posBuilder)
        }

        return rtBuilder
    }
}



