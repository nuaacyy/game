package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.MakeSoliderEvent
import com.point18.slg2d.world.event.MakeTrapEvent
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

//处理造兵
fun dealMakeSolider(
    areaCache: AreaCache,
    playerId: Long,
    soliderId: Int,
    makeType: Int,
    makeNum: Int,
    effectMap: HashMap<Int, Int>
): Int {
    // 判断造兵队列
    if (makeType == RESEARCH_LVUP_NORMAL) {
        val num = areaCache.barracksCache.findMakeQueueNum(playerId, ::isSolider)
        if (num >= 1) {
            return ResultCode.BARRACK_DUILIE_ERROR.code
        }
    }

    val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
    if (soliderProto == null) {
        return ResultCode.NO_PROTO.code
    }

    //判断该兵营是否正在造兵中
    val barracksVo = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)
    if (barracksVo == null) {
        return ResultCode.PARAMETER_ERROR.code
    }
    if (barracksVo.overTime != 0L && makeType == RESEARCH_LVUP_NORMAL) {
        return ResultCode.BARRACK_IN_ERROR.code
    }

    val nowTime = getNowTime()
    if (makeType == RESEARCH_LVUP_NORMAL) {
        val effValue = effectMap[MakeSoliderQuick] ?: 0
        val needTime =
            nowTime + (soliderProto.trainTime * 1000 * makeNum / (1 + effValue.toDouble() / 10000)).toLong()

        areaCache.barracksCache.makeSoliderUpdate(barracksVo, needTime)
        barracksVo.nowMakeNum = makeNum
    } else {
        barracksVo.soldierNum = barracksVo.soldierNum + makeNum
        targetAddVal(
            areaCache,
            barracksVo.playerId,
            MakeSoliderCount,
            asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), makeNum.toLong())
        )
        wpm.es.fire(
            areaCache,
            barracksVo.playerId,
            MAKE_SOLDIER_FINISH,
            MakeSoliderEvent(soliderProto.armyType, soliderProto.step, makeNum)
        )

        var makeSoliderAction = 0
        when {
            soliderProto.step == 1 -> makeSoliderAction = MAKE_SOLIDER_1
            soliderProto.step == 2 -> makeSoliderAction = MAKE_SOLIDER_2
            soliderProto.step == 3 -> makeSoliderAction = MAKE_SOLIDER_3
            soliderProto.step == 4 -> makeSoliderAction = MAKE_SOLIDER_4
        }

        wpm.es.fire(
            areaCache,
            playerId,
            PLAYER_ACTIVITY_CHANGE,
            PlayerActivityChange(makeSoliderAction, makeNum, 0)
        )
    }

    // 推送给客户端变更
    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        val notice = createBarracksChangeNotifier(barracksVo)
        notice.notice(session)
    }

    //同步兵营数据至home
    syncBarrack2Home(areaCache, playerId)

    //重算军团实力
    targetAddVal(areaCache, playerId, SoliderStrength)

    return ResultCode.SUCCESS.code
}

//处理造陷阱
fun dealMakeTrap(
    areaCache: AreaCache,
    playerId: Long,
    soliderId: Int,
    makeType: Int,
    makeNum: Int,
    effectMap: HashMap<Int, Int>
): Int {
    // 判断造陷阱队列
    if (makeType == RESEARCH_LVUP_NORMAL) {
        val num = areaCache.barracksCache.findMakeQueueNum(playerId, ::isTrap)
        if (num >= 1) {
            return ResultCode.BARRACK_DUILIE_ERROR.code
        }
    }

    val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
    if (soliderProto == null) {
        return ResultCode.NO_PROTO.code
    }

    //判断该兵营是否正在造陷阱中
    val barracksVo = areaCache.barracksCache.findBarracksByPlayerIdAndSoldierId(playerId, soliderId)

    if (barracksVo == null) {
        return ResultCode.NO_PROTO.code

    }
    if (barracksVo.overTime != 0L && makeType == RESEARCH_LVUP_NORMAL) {
        return ResultCode.BARRACK_IN_ERROR.code
    }

    //判断可建造陷阱数量
    var currentTotalTrapNum = 0
    for (barrack in areaCache.barracksCache.findBarracksByPlayerId(playerId)) {
        val solider = pcs.soliderCache.soliderProtoMap[barrack.soldierId]
        if (solider == null) {
            continue
        }
        if (!isTrap(solider.armyType)) {
            continue
        }
        currentTotalTrapNum += barrack.soldierNum + barrack.nowMakeNum +
            barrack.nowCureNum + barrack.nowEventCureNum + barrack.upNum
    }
    if (effectMap[TrapNumAdd] ?: 0 - currentTotalTrapNum < makeNum
    ) {
        return ResultCode.BARRACK_LESS_EMPTY_POS.code
    }

    // 重新计算需要总资源量
    val (ok, newRes) = resVoAddX(soliderProto.trainPriceMap, makeNum)
    if (!ok) {
        return ResultCode.BARRACK_GO_ALL_RES_ERROR.code

    }

    val nowTime = getNowTime()
    if (makeType == RESEARCH_LVUP_NORMAL) {
        val effValue = effectMap[MakeTrapQuick] ?: 0
        val needTime =
            nowTime + (soliderProto.trainTime * 1000 * makeNum / (1 + effValue.toDouble() / 10000)).toLong()

        areaCache.barracksCache.makeSoliderUpdate(barracksVo, needTime)
        barracksVo.nowMakeNum = makeNum
    } else {
        barracksVo.soldierNum = barracksVo.soldierNum + makeNum

        targetAddVal(
            areaCache,
            barracksVo.playerId,
            MakeTrapCount,
            asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), makeNum.toLong())
        )
        wpm.es.fire(
            areaCache,
            barracksVo.playerId,
            MAKE_TRAP_FINISH,
            MakeTrapEvent(soliderProto.armyType, soliderProto.step, makeNum)
        )

        var makeSoliderAction = 0
        when {
            soliderProto.step == 1 -> makeSoliderAction = MAKE_SOLIDER_1
            soliderProto.step == 2 -> makeSoliderAction = MAKE_SOLIDER_2
            soliderProto.step == 3 -> makeSoliderAction = MAKE_SOLIDER_3
            soliderProto.step == 4 -> makeSoliderAction = MAKE_SOLIDER_4
        }

        wpm.es.fire(
            areaCache,
            playerId,
            PLAYER_ACTIVITY_CHANGE,
            PlayerActivityChange(makeSoliderAction, makeNum, 0)
        )
    }

    // 推送给客户端变更
    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        val notice = createBarracksChangeNotifier(barracksVo)
        notice.notice(session)
    }

    //同步兵营数据至home
    syncBarrack2Home(areaCache, playerId)

    //重算军团实力
    targetAddVal(areaCache, playerId, TrapStrength)

    return ResultCode.SUCCESS.code
}
