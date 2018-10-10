package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.world.common.syncBarrack2Home
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

data class CancelCureSoliderRt(
    var rt: Int = ResultCode.SUCCESS.code,
    var cancelMap: HashMap<Int, Int> = hashMapOf()
)

// 取消治疗兵
fun cancelCureSolider(areaCache: AreaCache, playerId: Long, eventCure: Int): CancelCureSoliderRt {

    // 第三个参数是队列ID ,就只有1
    val barracksVos = areaCache.barracksCache.findCureSoliderListByCureQueue(playerId, eventCure, 1)
    if (barracksVos.size == 0) {
        return CancelCureSoliderRt(ResultCode.BARRACK_NO_IN_ERROR.code)
    }

    for (barracksVo in barracksVos) {
        val soliderProto = pcs.soliderCache.soliderProtoMap[barracksVo.soldierId]
        if (soliderProto == null) {
            return CancelCureSoliderRt(ResultCode.NO_PROTO.code)
        }
    }

    val cancelCureMap = hashMapOf<Int, Int>()
    for (barracksVo in barracksVos) {

        if (eventCure == EventCure) {
            cancelCureMap[barracksVo.soldierId] = barracksVo.nowEventCureNum
            // 修改数据
            areaCache.barracksCache.eventCureSoliderUpdate(barracksVo, 0)
            barracksVo.nowEventCureNum = 0
            barracksVo.eventCureQueue = 0
        } else {
            cancelCureMap[barracksVo.soldierId] = barracksVo.nowCureNum
            // 修改数据
            areaCache.barracksCache.cureSoliderUpdate(barracksVo, 0)
            barracksVo.nowCureNum = 0
            barracksVo.cureQueue = 0
        }


        // 推送给客户端变更
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val notice = createBarracksChangeNotifier(barracksVo)
            notice.notice(session)
        }
    }

    //同步兵营数据至home
    syncBarrack2Home(areaCache, playerId)

    return CancelCureSoliderRt(ResultCode.SUCCESS.code, cancelCureMap)
}