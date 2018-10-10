package com.point18.slg2d.world.module.instance

import com.point18.slg2d.common.constg.INSTANCE_OPEN
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.checkStrength
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import java.util.*

val instanceFightCheck = InstanceFightCheck()

//推图战斗检测
class InstanceFightCheck {
    data class CheckInstanceFightCondRt(
        val rt: Int,
        val instanceProto: InstanceProto? = null,
        val unitTeamProtoList: List<UnitTeamProto>? = null,
        val clearanceProtoList: List<ClearanceProto>? = null,
        val instanceUnitProto: InstanceUnitProto? = null
    )

    fun checkInstanceFightCond(session: PlayerSession, floorId: Int): CheckInstanceFightCondRt {
        var rt = ResultCode.SUCCESS.code

        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, INSTANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt = uiConditionRt
            return CheckInstanceFightCondRt(rt)
        }

        val instanceProto = pcs.instanceProtoCache.protoMap[floorId]
        if (instanceProto == null) {
            rt = ResultCode.NO_PROTO.code
            return CheckInstanceFightCondRt(rt)
        }

        //验证部队配置
        val unitTeamProtoList = LinkedList<UnitTeamProto>()
        instanceProto.unitTeamList.forEach {
            val unitTeamProto = pcs.unitTeamProtoCache.protoMap[it]
            if (unitTeamProto == null) {
                rt = ResultCode.NO_PROTO.code
                return CheckInstanceFightCondRt(rt)
            }
            unitTeamProtoList.add(unitTeamProto)
        }

        //验证星级条件
        val clearanceProtoList = LinkedList<ClearanceProto>()
        instanceProto.clearanceList.forEach {
            val clearanceProto = pcs.clearanceProtoCache.clearanceMap[it]
            if (clearanceProto == null) {
                rt = ResultCode.NO_PROTO.code
                return CheckInstanceFightCondRt(rt)
            }
            clearanceProtoList.add(clearanceProto)
        }

        val instanceUnitProto = pcs.instanceUnitProtoCache.protoMap[instanceProto.unitId]
        if (instanceUnitProto == null) {
            rt = ResultCode.NO_PROTO.code
            return CheckInstanceFightCondRt(rt)
        }

        val conResult = checkMainOpenCon(session.areaCache, player, instanceUnitProto.mainOpenConMap)
        if (!conResult) {
            rt = ResultCode.UI_CONDITION_ERROR.code
            return CheckInstanceFightCondRt(rt)
        }

        val instanceVo = session.areaCache.instanceCache.findInstance(session.playerId)
        if (instanceVo == null) {
            rt = ResultCode.PARAMETER_ERROR.code
            return CheckInstanceFightCondRt(rt)
        }

        if (instanceProto.unitOpenCon != 0 && instanceVo.nowFight != 0 && instanceVo.nowFight <= instanceProto.unitOpenCon) {
            rt = ResultCode.PARAMETER_ERROR.code
            return CheckInstanceFightCondRt(rt)
        }

        val checkCost = checkStrength(session.areaCache, player, instanceProto.winStrength)
        if (checkCost != ResultCode.SUCCESS) {
            rt = checkCost.code
            return CheckInstanceFightCondRt(rt)
        }

        return CheckInstanceFightCondRt(rt, instanceProto, unitTeamProtoList, clearanceProtoList, instanceUnitProto)
    }

    private fun checkMainOpenCon(areaCache: AreaCache, player: Player, con: Map<Int, List<Int>>): Boolean {
        for ((conType, vs) in con) {
            if (conType == 1) {
                val infoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(player.id)
                if (infoByHome == null) {
                    return false
                }
                val buildingLv = infoByHome.findBuildingMaxLv(vs[0])
                if (buildingLv < vs[1]) {
                    return false
                }
            } else if (conType == 2) {
                if (player.kingLv < vs[1]) {
                    return false
                }
            } else {
                return false
            }
        }

        return true
    }
}

