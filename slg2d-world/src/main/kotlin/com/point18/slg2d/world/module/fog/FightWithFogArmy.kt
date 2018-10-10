package com.point18.slg2d.world.module.fog

import com.google.protobuf.MessageLite
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Hero
import com.point18.slg2d.world.module.fightdomain.createFightData
import com.point18.slg2d.world.module.fightdomain.createFightDataByFog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import pb4client.FightWithFogArmy
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.costSolider
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.fightStrategy.soliderFight
import pb4client.FightWithFogArmyRt
import java.util.*


class FightWithFogArmy : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        msg as FightWithFogArmy
        val rt = dealFightWithFog(session.areaCache, session.playerId, msg)
        session.sendMsg(MsgType.FightWithFogArmy_1573, rt.build())
    }

    private fun dealFightWithFog(
        areaCache: AreaCache,
        playerId: Long,
        msg: FightWithFogArmy
    ): FightWithFogArmyRt.Builder {
        val fogId = msg.fogId
        val rt = FightWithFogArmyRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        //校验迷雾
        val fog = areaCache.fogCache.findFogById(playerId, fogId)
        if (fog == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        if (fog.state != NotWin) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val fogProto = pcs.mapOpenProtoCache.mapOpenMap[fog.fogId]
        if (fogProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }
        fogProto.unitConditionList.forEach {
            val checkFog = areaCache.fogCache.findFogById(playerId, it)
            if (checkFog == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            if (checkFog.state != UnLocked) {
                rt.rt = ResultCode.FOG_CONDITION_NOT_UNLOCK.code
                return rt
            }
        }
        //校验英雄
        val heroList = LinkedList<Hero>()
        msg.herosList.forEach {
            val hero = areaCache.heroCache.findHeroById(playerId, it)
            if (hero == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            if (hero.posState != IN_CITY) {
                rt.rt = ResultCode.HERO_POS_STATE_ERROR.code
                return rt
            }
            heroList.add(hero)
        }
        //校验士兵
        msg.solidersList.forEach {
            if (it.num <= 0) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            val barrack = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, it.protoId)
            if (barrack == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            if (barrack.soldierNum < it.num) {
                rt.rt = ResultCode.NO_ENOUGH_SOLIDER_IN_BARRACK_ERROR.code
                return rt
            }
        }
        //进行战斗
        val soliderMap = hashMapOf<Int, Int>()
        msg.solidersList.forEach {
            costSolider(areaCache, playerId, it.protoId, it.num)
            soliderMap[it.protoId] = it.num
        }

        val atkFightData = createFightData(playerId, heroList, soliderMap)
        val defFightData = createFightDataByFog(fog)
        val fightResultData = soliderFight.doSoliderFight(
            areaCache, PVE_FIGHT_FOG_ACTION, 0, 0,
            false, 0, 0, LinkedList(),
            LinkedList(), atkFightData, defFightData
        )
        val fightResult = fightResultData.fightResult
        val atkInfoAfterFight =
            atkFightData.calSoliderAfterFightWithOutHospital(pcs.basicProtoCache.pvpAttackerDieRate)
        val defInfoAfterFight = defFightData.calSoliderAfterFightWithOutHospital(10000)
        //更新攻击方部队数据（全部是行军组士兵）
        atkInfoAfterFight.refreshWithOutGroup(areaCache, playerId)

        val leftSoliderMap = hashMapOf<Int, Int>()
        defInfoAfterFight.leftSoliderDataMap.forEach {
            for (info in it.value.soliderMap) {
                if (info.value <= 0) {
                    continue
                }
                leftSoliderMap[info.key] = (leftSoliderMap[info.key] ?: 0) + info.value
            }
        }

        fog.soliderMap = leftSoliderMap
        if (fog.soliderMap.isEmpty()) {
            fog.state = NotGetReward
        }
        rt.state = fog.state
        rt.fightDetail = toJson(fightResultData)
        rt.power = fog.calFogPower()

        return rt
    }
}