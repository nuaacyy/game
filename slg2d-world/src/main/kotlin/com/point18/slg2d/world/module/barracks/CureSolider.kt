package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.event.CureSoliderEvent
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.targetAddVal
import java.util.*
import java.util.Arrays.asList

// 治疗兵
fun cureSolider(
    areaCache: AreaCache,
    playerId: Long,
    cureType: Int,
    trapOrSolider: Int,
    cureMap: HashMap<Int, Int>,
    effectMap: HashMap<Int, Int>,
    eventCure: Int
): Int {

    if (cureType == RESEARCH_LVUP_NORMAL) {
        // 判断研发队列
        if (trapOrSolider == CureTrap) {
            val trapNum = areaCache.barracksCache.findCureSoliderNum(playerId, ::isTrap)
            if (trapNum >= 1) {
                return ResultCode.BARRACK_CURE_DUILIE_ERROR.code
            }
        } else if (trapOrSolider == CureSolider) {
            val soliderNum = areaCache.barracksCache.findCureSoliderNum(playerId, ::isSolider)
            if (soliderNum >= 1) {
                return ResultCode.BARRACK_CURE_DUILIE_ERROR.code
            }
        } else {
            return ResultCode.PARAMETER_ERROR.code
        }
    }

    var allNeedTime = 0

    val addNum = effectMap[CureQuick] ?: 0

    // 检测消息正确性
    val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)
    for (cureInfo in cureMap) {
        val soliderId = cureInfo.key
        val cureNum = cureInfo.value
        val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
        if (soliderProto == null) {
            return ResultCode.NO_PROTO.code
        }
        val barrack = barrackMap[soliderId]
        if (barrack == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        if (eventCure == EventCure) {
            if (barrack.canEventCureNum < cureNum) {
                return ResultCode.PARAMETER_ERROR.code
            }
        } else {
            if (barrack.canCureNum < cureNum) {
                return ResultCode.PARAMETER_ERROR.code
            }
        }

        // 重新计算需要总资源量
        var cureTime: Double
        if (eventCure == EventCure) {
            cureTime = soliderProto.cureTimeActivity
        } else {
            cureTime = soliderProto.cureTime
        }
        allNeedTime += Math.ceil(cureTime * cureNum.toDouble() / (1 + (addNum.toDouble() / 10000))).toInt()
    }

    val nowTime = getNowTime()
    // 判断资源
    val action = ACTION_CURE_SOLIDER
    if (cureType == RESEARCH_LVUP_NORMAL) {
        for (cureInfo in cureMap) {
            val soliderId = cureInfo.key
            val cureNum = cureInfo.value
            val barracksVo = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
            if (barracksVo == null) {
                continue
            }

            val needTime = nowTime + (allNeedTime * 1000)
            if (eventCure == EventCure) {
                areaCache.barracksCache.eventCureSoliderUpdate(barracksVo, needTime)
                barracksVo.nowEventCureNum = barracksVo.nowEventCureNum + cureNum
                barracksVo.eventCureQueue = 1
            } else {
                areaCache.barracksCache.cureSoliderUpdate(barracksVo, needTime)
                barracksVo.nowCureNum = barracksVo.nowCureNum + cureNum
                barracksVo.cureQueue = 1
            }

            // 推送给客户端变更
            val session = fetchOnlinePlayerSession(areaCache, playerId)
            if (session != null) {
                val notice = createBarracksChangeNotifier(barracksVo)
                notice.notice(session)
            }
        }
    } else {
        // 补齐资源的模式
        for (cureInfo in cureMap) {
            val soliderId = cureInfo.key
            val cureNum = cureInfo.value
            val barracksVo = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
            if (barracksVo == null) {
                continue
            }
            if (eventCure != EventCure) {
                barracksVo.canCureNum = barracksVo.canCureNum - cureNum
            } else {
                barracksVo.canEventCureNum = barracksVo.canEventCureNum - cureNum
            }
            barracksVo.soldierNum = barracksVo.soldierNum + cureNum

            //添加统计
            val soliderProto = pcs.soliderCache.soliderProtoMap[barracksVo.soldierId]
            if (soliderProto == null) {
                continue
            }
            if (isSolider(soliderProto.armyType)) {
                targetAddVal(
                    areaCache,
                    playerId,
                    TotalCureNum,
                    asList(cureNum.toLong())
                )
                wpm.es.fire(areaCache, playerId, CURE_SOLDIER,
                    CureSoliderEvent(cureNum)
                )
            }

            //重算军团实力
            targetAddVal(areaCache, playerId, SoliderStrength)
            targetAddVal(areaCache, playerId, TrapStrength)

            // 推送给客户端变更
            val session = fetchOnlinePlayerSession(areaCache, playerId)
            if (session != null) {
                val notice = createBarracksChangeNotifier(barracksVo)
                notice.notice(session)
            }
        }
    }

    //同步兵营数据至home
    syncBarrack2Home(areaCache, playerId)

    return ResultCode.SUCCESS.code
}

