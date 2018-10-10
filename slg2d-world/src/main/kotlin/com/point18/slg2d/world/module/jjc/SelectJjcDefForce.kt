package com.point18.slg2d.world.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.JjcDef
import com.point18.slg2d.common.constg.JjcFight
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.findJjcRobotByRank
import com.point18.slg2d.world.common.fetchChallenge
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.SelectJjcDefForce
import pb4client.SelectJjcDefForceRt

// 查询某玩家竞技场防守阵容
class SelectJjcAtkForceDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = selectJjcAtkForce(session, msg as SelectJjcDefForce)
        session.sendMsg(MsgType.SelectJjcAtkForce_724, rt)
    }

    private fun selectJjcAtkForce(session: PlayerSession, selectMsg: SelectJjcDefForce): (SelectJjcDefForceRt) {
        val rt = SelectJjcDefForceRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.fightValue = 0
        rt.jjcDefForceNpcTeamId = 0
        rt.defPlayerId = 0

        val areaCache = session.areaCache
        val rank = selectMsg.selectRank

        if (rank == 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        val defPlayer = areaCache.playerCache.findPlayerByJjcRank(rank)
        val jjcChallengeInfo = fetchChallenge(areaCache, rank)
        rt.addAllJjcHeros(jjcChallengeInfo.jjcHerosList)

        if (defPlayer == null) {
            rt.jjcDefForceNpcTeamId = findJjcRobotByRank(rank)
        } else {
            val defForces = areaCache.armyPlanCache.findArmyPlan(
                defPlayer.id,
                JjcFight,
                JjcDef
            )
            if (defForces == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
            var defPlayerPower = 0L
            val defHeroMap = defForces.heroMap
            for ((_, heroId) in defHeroMap) {
                val tmpHero = areaCache.heroCache.findHeroById(defPlayer.id, heroId) ?: continue
                defPlayerPower += tmpHero.heroStrength
            }
            rt.fightValue = defPlayerPower
            rt.defPlayerId = defPlayer.id
        }

        return rt.build()
    }

}